package com.example.booksalonappointment.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.booksalonappointment.R
import com.example.booksalonappointment.databinding.ActivityBookingSummerBinding
import com.example.booksalonappointment.model.remote.Constants.BASE_IMAGE_URL
import com.example.booksalonappointment.model.remote.response.BarberService
import com.example.booksalonappointment.view.adapter.SummerServiceAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class BookingSummerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookingSummerBinding
    private lateinit var adapter: SummerServiceAdapter
    private lateinit var services: ArrayList<BarberService>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookingSummerBinding.inflate(layoutInflater)
        services = ArrayList()
        setContentView(binding.root)
        setAdapter()
        binding.apply {
            backService.setOnClickListener {
                super.onBackPressed()
            }

            btnCancel.setOnClickListener {
                startActivity(
                    Intent(this@BookingSummerActivity, DashBoardActivity::class.java)
                )
                finish()
            }

            btnContinue.setOnClickListener {
                startActivity(
                    Intent(this@BookingSummerActivity, AppointmentDetailsActivity::class.java)
                )
                finish()
            }
        }
     //   initData()
    }

    private fun setAdapter() {
        adapter = SummerServiceAdapter(this, services)
        binding.apply {

           binding.recyclerViewServices.layoutManager = LinearLayoutManager(this@BookingSummerActivity)
            binding.recyclerViewServices.adapter = adapter
        }
    }

   /* private fun initData() {

        var cost = 0
        var duration = 0
        services = getBarberServices().apply {
            for (i in this) {
                cost += i.cost.toInt()
                duration += i.duration.toInt()
            }
            binding.apply {
                apptDuration.text = "$duration Minutes"
                apptCost.text = "Total Cost: $$cost"
                apptDate.text = getApptDateAndTime()
            }
        }
        setAdapter()
        val selectedBarber = pref.getString("barber_name"," ")
        binding.apply {
            selectedBarberName.text = selectedBarber
           // Glide.with(this@BookingSummerActivity)
               // .load(BASE_IMAGE_URL + selectedBarber.profilePic)
               // .into(barberImage)
        }
    }*/
}




