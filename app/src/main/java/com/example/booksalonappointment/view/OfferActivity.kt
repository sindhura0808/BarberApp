package com.example.booksalonappointment.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.booksalonappointment.R
import com.example.booksalonappointment.databinding.ActivityOffersBinding

class OfferActivity : AppCompatActivity() {

    lateinit var binding:ActivityOffersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityOffersBinding.inflate(layoutInflater)


        setContentView(R.layout.activity_offers)
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