package com.example.booksalonappointment.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.booksalonappointment.databinding.ActivityRegistrationBinding
import com.example.booksalonappointment.model.Repository
import com.example.booksalonappointment.viewmodel.registration.RegistrationViewModel
import com.example.booksalonappointment.viewmodel.registration.RegistrationViewModelFactory
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.messaging.FirebaseMessaging

class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationBinding
    private lateinit var viewModel: RegistrationViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registerLogin.setOnClickListener {
            startActivity(Intent(this, LogInActivity::class.java))
            finish()
        }
        FirebaseMessaging.getInstance().token.addOnCompleteListener {
            if (it.isSuccessful) {
                viewModel.fcmToken.value = it.result
            }
        }
        setUpViewModel()
        setUpInputObserver()
        setUpAPIObserver()
    }
    private fun setUpViewModel() {
        val vmFactory = RegistrationViewModelFactory(Repository())
        viewModel = ViewModelProvider(this, vmFactory)[RegistrationViewModel::class.java]
        binding.viewModel = viewModel
    }

    private fun setUpAPIObserver() {
        viewModel.registrationResponse.observe(this) {
            openDialogMessage(it!!.status, it.message)
        }

        viewModel.error.observe(this) {
            openDialogMessage(1, it!!)
        }
    }

    private fun openDialogMessage(status: Int, message: String) {
        val title = if (status == 0) "Welcome, Registration Successful!" else "Sorry, Registration Failed"
        val builder = AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Go to Login") {_, _ -> finish() }
            .setNeutralButton("Try again", null)
        val alertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

    private fun setUpInputObserver(){
        viewModel.isMobileNumberNotEmpty.observe(this) {
            binding.edtMobile.error = if (it!!) null else "Required"
        }

        viewModel.isMobileNumberValid.observe(this) {
            binding.edtMobile.error = if (it!!) null else "Invalid Number"
        }

        viewModel.isPasswordNotEmpty.observe(this) {
            binding.edtPassword.error = if (it!!) null else "Required"
        }

        viewModel.isConfirmPasswordNotEmpty.observe(this) {
            binding.edtconfirmPassword.error = if (it!!) null else "Required"
        }

        viewModel.matchPassword.observe(this) {
            binding.edtconfirmPassword.error = if (it!!) null else "Please check again"
        }
    }

}