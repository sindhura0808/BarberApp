@file:Suppress("NAME_SHADOWING")

package com.example.booksalonappointment.view

import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.booksalonappointment.databinding.ActivityTimeSelectionBinding
import com.example.booksalonappointment.model.Repo.Repository
import com.example.booksalonappointment.model.remote.APIService
import com.example.booksalonappointment.model.remote.response.time.Slot
import com.example.booksalonappointment.view.adapter.SelectDateAdapter
import com.example.booksalonappointment.view.adapter.TimeSelectionAdapter
import com.example.booksalonappointment.viewmodel.timeselection.TimeSelectionViewModel
import com.example.booksalonappointment.viewmodel.timeselection.TimeSelectionViewModelFactory
import java.text.SimpleDateFormat
import java.util.*

class TimeSelectionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTimeSelectionBinding
    private lateinit var timeAdapter: TimeSelectionAdapter
    lateinit var dateAdapter: SelectDateAdapter
    lateinit var viewModel: TimeSelectionViewModel
    lateinit var factory: TimeSelectionViewModelFactory
    lateinit   var selectedTime:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTimeSelectionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewModel()
        setupObservers()
        binding.apply {
            backService.setOnClickListener {
                super.onBackPressed()
            }
            btnCancel.setOnClickListener {
                startActivity(
                    Intent(this@TimeSelectionActivity, DashBoardActivity::class.java)
                )
                finish()
            }
            btnContinue.setOnClickListener {
                if (!selectedDate.text.equals("N/A")) {
                    startActivity(
                        Intent(this@TimeSelectionActivity, BookingSummerActivity::class.java)
                    )
                    finish()
                } else {
                    Toast.makeText(
                        this@TimeSelectionActivity,
                        "Please choose a date and time",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }


    private fun setupViewModel() {
        factory = TimeSelectionViewModelFactory(Repository(APIService.getInstance()))
        viewModel = ViewModelProvider(this, factory)[TimeSelectionViewModel::class.java]
            viewModel.loadAppointmentTime()
        binding.viewModel = viewModel

    }


    private fun setupObservers() {
        viewModel.currentAppointmentsResponse.observe(this) {
            val aSlots = it.slots
            dateAdapter = SelectDateAdapter(this, aSlots, viewModel, binding.selectedDate)
            binding.rvDates.adapter = dateAdapter
            binding.rvDates.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

            viewModel.setAppointmentsDate(aSlots[0].date)

        }
        binding.timeButton.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener= TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                selectedTime= SimpleDateFormat("HH:MM").format(cal.time)
                binding.timeTv.text = selectedTime
                val pref=getSharedPreferences("users",AppCompatActivity.MODE_PRIVATE)
                pref.edit().apply{
                    putString("selected_Time" , selectedTime)
                    apply()
                }
            }
            TimePickerDialog(this,timeSetListener,cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE), true).show()
        }

        /* viewModel.appointmentsDateLiveData.observe(this) { date ->
             viewModel.currentAppointmentsLiveData.value!!.forEach(){
                 if (it.date == date) {
                     timeAdapter = TimeSelectionAdapter(this, it.slots, viewModel)
                     binding.recyclerViewTime.adapter = timeAdapter
                     binding.recyclerViewTime.layoutManager = GridLayoutManager(this, 4)



                 }
             }
         }*/
    }

}

