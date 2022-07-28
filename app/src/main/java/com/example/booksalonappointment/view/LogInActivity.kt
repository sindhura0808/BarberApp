package com.example.booksalonappointment.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.booksalonappointment.R
import com.example.booksalonappointment.databinding.ActivityLogInBinding
import com.google.firebase.messaging.FirebaseMessaging

class LogInActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLogInBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginRegister.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
        }

        binding.btnLogin.setOnClickListener {
            startActivity(Intent(this, HomeScreenActivity::class.java))
            finish()
        }
        binding.txtSkip.setOnClickListener {
            startActivity(Intent(this, DashBoardActivity::class.java))
            finish()
        }
    }
}