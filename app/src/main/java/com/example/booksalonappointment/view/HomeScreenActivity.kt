package com.example.booksalonappointment.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.booksalonappointment.R
import com.example.booksalonappointment.databinding.ActivityDashBoardBinding
import com.example.booksalonappointment.databinding.ActivityHomeScreenBinding

class HomeScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}