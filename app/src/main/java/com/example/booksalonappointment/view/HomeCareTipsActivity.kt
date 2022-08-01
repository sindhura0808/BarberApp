package com.example.booksalonappointment.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.booksalonappointment.R
import com.example.booksalonappointment.databinding.ActivityHomeScreenBinding
import com.example.booksalonappointment.databinding.ActivityHomecaretipsBinding
import com.example.booksalonappointment.databinding.ActivityOffersBinding

class HomeCareTipsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_homecaretips)
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