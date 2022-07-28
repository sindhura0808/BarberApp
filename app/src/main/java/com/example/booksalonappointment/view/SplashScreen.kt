package com.example.booksalonappointment.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.booksalonappointment.R

class SplashScreen: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        Handler().postDelayed({
            var intent: Intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)
            finish()
        }, 1000)

    }
}
