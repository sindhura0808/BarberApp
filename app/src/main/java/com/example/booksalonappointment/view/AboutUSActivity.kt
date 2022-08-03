package com.example.booksalonappointment.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.booksalonappointment.R
import com.example.booksalonappointment.databinding.ActivityAboutUsactivityBinding

class AboutUSActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAboutUsactivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityAboutUsactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar))
        // calling the action bar
        var actionBar = getSupportActionBar()

        // showing the back button in action bar
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24)
            actionBar.setDisplayHomeAsUpEnabled(true)
        }
    }

}