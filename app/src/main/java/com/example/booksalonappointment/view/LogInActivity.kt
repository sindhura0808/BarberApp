package com.example.booksalonappointment.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.booksalonappointment.R
import com.example.booksalonappointment.databinding.ActivityLogInBinding
import com.example.booksalonappointment.model.Repository
import com.example.booksalonappointment.model.remote.response.LogInResponse
import com.example.booksalonappointment.viewmodel.login.LoginViewModel
import com.example.booksalonappointment.viewmodel.login.LoginViewModelFactory
import com.example.booksalonappointment.viewmodel.registration.RegistrationViewModel
import com.google.firebase.messaging.FirebaseMessaging

class LogInActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLogInBinding
    private lateinit var viewModel: LoginViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginRegister.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
        }

        binding.txtSkip.setOnClickListener {
            startActivity(Intent(this, DashBoardActivity::class.java))
            finish()
        }
        binding.forgotpassword.setOnClickListener{
            startActivity(Intent(this,ForgotPasswordActivity::class.java))
        }

        FirebaseMessaging.getInstance().token.addOnCompleteListener {
            if(it.isSuccessful) {
                    viewModel.fcmToken.postValue(it.result)
                    Log.d("FCM_Token", "FCM_TOKEN: ${it.result}")
            }
        }
        setupViewModel()
        setupObservers()

    }
    private fun setupViewModel() {
        val viewmodelFactory = LoginViewModelFactory(Repository())
        viewModel = ViewModelProvider(this, viewmodelFactory)[LoginViewModel::class.java]
        binding.viewModel = viewModel
    }

    private fun setupObservers() {
        viewModel.loginResponse.observe(this) {
            saveUser(it)
            Toast.makeText(this,it.userId, Toast.LENGTH_LONG).show()
            startActivity(Intent(baseContext, DashBoardActivity::class.java))
            finish()
        }
        viewModel.error.observe(this) {
            openDialog(it!!)
        }
    }

    private fun openDialog(message: String) {
        val builder = AlertDialog.Builder(this)
            .setTitle("Sorry, Wrong Phone number and password entered!!")
            .setMessage(message)
            .setNeutralButton("Try again", null)
        val alertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

    private fun saveUser(user: LogInResponse) {
       val pref = getSharedPreferences("users", MODE_PRIVATE)

      pref.edit().apply{
           putString("user_id", user.userId)
           putString("mobile_no", user.mobileNo)
           putString("fcm_Token", viewModel.fcmToken.value!!)
           apply()
       }
   }
}

