package com.example.booksalonappointment.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModelProvider
import com.example.booksalonappointment.databinding.ActivityRegistrationBinding
import com.example.booksalonappointment.model.Repo.Repository
import com.example.booksalonappointment.model.remote.APIService
import com.example.booksalonappointment.viewmodel.registration.RegistrationViewModel
import com.example.booksalonappointment.viewmodel.registration.RegistrationViewModelFactory
import com.google.firebase.messaging.FirebaseMessaging

class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationBinding
    private lateinit var viewModel: RegistrationViewModel
    lateinit var phone: String
    lateinit var password: String
    lateinit var confirmPassword: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpViewModel()

        binding.btnRegister.setOnClickListener {
            phone = binding.edtMobile.text.toString()
            password = binding.edtPassword.text.toString()
            confirmPassword = binding.edtconfirmPassword.text.toString()
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
            if (password != confirmPassword) {
                check = false
                message = "Password not same."
            }
            if (check) {
                FirebaseMessaging.getInstance().token.addOnCompleteListener {
                    if (it.isSuccessful) {
                        viewModel.onRegisterClick(phone,password,it.result)
                    }
                }
            } else {
                val builder = AlertDialog.Builder(this)
                    .setTitle("Register Error")
                    .setMessage(message)
                    .setPositiveButton("Ok") { _, _ ->
                    }
                val alertDialog: AlertDialog = builder.create()
                alertDialog.setCancelable(true)
                alertDialog.show()
            }


        }
        binding.registerLogin.setOnClickListener{
            val intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)
    }

        viewModel.userLiveData.observe(this) {
            val builder = AlertDialog.Builder(this)
                .setTitle("Registration Successfull!!")
                .setMessage("Please log in!!")
                .setPositiveButton("Login") { _, _ ->
                    val intent = Intent(this, LogInActivity::class.java)
                    intent.putExtra(PHONE, phone)
                    intent.putExtra(PASSWORD, password)
                    startActivity(intent)
                }
                .setNegativeButton("Cancel") { _, _ ->
                }
            val alertDialog: AlertDialog = builder.create()
            alertDialog.setCancelable(false)
            alertDialog.show()
        }
    }


private fun setUpViewModel() {
    val vmFactory = RegistrationViewModelFactory(Repository(APIService.getInstance()))
    viewModel = ViewModelProvider(this, vmFactory)[RegistrationViewModel::class.java]
    binding.viewModel = viewModel
}



    companion object {
        const val PHONE = "phone"
        const val PASSWORD = "password"
    }
}