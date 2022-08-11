package com.example.booksalonappointment.view

import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.text.SimpleDateFormat
import android.view.View
import com.example.booksalonappointment.R
import com.example.booksalonappointment.databinding.ActivityTimeSelectBinding
import java.util.*

class TimeSelectActivity : AppCompatActivity() {
    lateinit var binding:ActivityTimeSelectBinding
    lateinit   var selectedTime:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityTimeSelectBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.timeButton.setOnClickListener {
            val cal =Calendar.getInstance()
            val timeSetListener=TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                 selectedTime=SimpleDateFormat("HH:MM").format(cal.time)
           binding.timeTv.text = selectedTime
            }
            TimePickerDialog(this,timeSetListener,cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE), true).show()
        }

        binding.apply {
            backService.setOnClickListener {
                super.onBackPressed()
                finish()
            }
            btnCancel.setOnClickListener {
                startActivity(
                    Intent(this@TimeSelectActivity, DashBoardActivity::class.java)
                )
                finish()
            }
            btnContinue.setOnClickListener {
                val pref = getSharedPreferences("users", AppCompatActivity.MODE_PRIVATE)
                pref.edit().apply{
                    putString("selectedTime", selectedTime)

                    apply()
                }

                startActivity(
                    Intent(this@TimeSelectActivity, BookingSummerActivity::class.java)
                )
                finish()
            }
        }
    }

}