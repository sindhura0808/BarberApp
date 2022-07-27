package com.example.booksalonappointment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

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
