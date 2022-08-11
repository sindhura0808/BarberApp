package com.example.booksalonappointment.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModelProvider
import com.example.booksalonappointment.databinding.ActivityLogInBinding
import com.example.booksalonappointment.model.Repo.Repository
import com.example.booksalonappointment.model.remote.APIService
import com.example.booksalonappointment.model.remote.response.LogInResponse
import com.example.booksalonappointment.viewmodel.login.LoginViewModel
import com.example.booksalonappointment.viewmodel.login.LoginViewModelFactory
import com.google.firebase.messaging.FirebaseMessaging

class LogInActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLogInBinding
    private lateinit var viewModel: LoginViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewModel()
        binding.loginRegister.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
        }

        if (intent.extras?.get("PHONE") != null && intent.extras?.get("PASSWORD") != null) {
            val phone = intent.extras?.get("PHONE") as String
            val password = intent.extras?.get("PASSWORD") as String
            viewModel.onLogin(phone, password)
        }
        binding.btnLogin.setOnClickListener {
            val phone = binding.edtPhone.text.toString()
            val password = binding.edtPassword.text.toString()
            var check = true
            var message = ""
            if (phone.length < 8) {
                check = false
                message = "Mobile Number should at least 8 digits"
            }
            if (phone.length > 13) {
                check = false
                message = "Mobile Number should not more than 13 digits"
            }
            if (!phone.isDigitsOnly()) {
                check = false
                message = "Mobile Number should be digits"
            }
            if (password.length < 8) {
                check = false
                message = "Password should at least 8 digits"
            }
            if (check) {
                viewModel.onLogin(phone, password)
            } else {
                val builder = AlertDialog.Builder(this)
                    .setTitle("Login Error")
                    .setMessage(message)
                    .setPositiveButton("Ok") { _, _ ->
                    }
                val alertDialog: AlertDialog = builder.create()
                alertDialog.setCancelable(true)
                alertDialog.show()
            }

        }

        binding.forgotpassword.setOnClickListener {
            startActivity(Intent(this, ForgotPasswordActivity::class.java))
        }

        viewModel.loginResponse.observe(this) {
            saveUser(it)
            FirebaseMessaging.getInstance().token.addOnCompleteListener {
                if (it.isSuccessful) {
                    viewModel.updateFCM(it.result)
                    viewModel.fcmToken.postValue(it.result)
                    Log.d("FCM_Token", "FCM_TOKEN: ${it.result}")

                }
            }
            val intent = Intent(this, DashBoardActivity::class.java)
            Log.e(LOGIN_INFO, it.toString())
            startActivity(intent)
        }

    }

    private fun setupViewModel() {
        val viewmodelFactory = LoginViewModelFactory(Repository(APIService.getInstance()))
        viewModel = ViewModelProvider(this, viewmodelFactory)[LoginViewModel::class.java]
        binding.viewModel = viewModel
    }


    private fun saveUser(user: LogInResponse) {
        val pref = getSharedPreferences("users", MODE_PRIVATE)
        pref.edit().apply {
            putString("user_id", user.userId)
            putString("mobile_no", user.mobileNo)
            apply()
        }
    }

    companion object {
        const val LOGIN_INFO = "login_info"
    }
}

