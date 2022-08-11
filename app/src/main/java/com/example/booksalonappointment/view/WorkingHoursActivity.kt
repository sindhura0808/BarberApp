package com.example.booksalonappointment.view

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.booksalonappointment.R
import com.example.booksalonappointment.databinding.ActivityWorkingHoursBinding
import java.time.LocalDateTime

class WorkingHoursActivity : AppCompatActivity() {

    private lateinit var binding:ActivityWorkingHoursBinding
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityWorkingHoursBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(findViewById(R.id.toolbar))

        binding.apply {
            backService.setOnClickListener {
                super.onBackPressed()
                finish()
            }
        }
        initOpenTv()
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun initOpenTv() {
        binding.apply {
            val currentDate = LocalDateTime.now()
            val day = currentDate.dayOfWeek
            val hour = currentDate.hour
            val businessHours = intArrayOf(9, 10, 11, 12, 13, 14, 15, 16) // 9 am - 4 pm CST
            val satHours = intArrayOf(9, 10, 11, 12, 13) // 9 am - 1 pm CST
            if (hour !in businessHours) {
                openTv.text = getString(R.string.closed_caps)
            } else if (day.toString() == "SATURDAY" && hour !in satHours) {
                openTv.text = getString(R.string.closed_caps)
            }
        }
    }
}