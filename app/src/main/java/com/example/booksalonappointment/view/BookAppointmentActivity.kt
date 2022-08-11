package com.example.booksalonappointment.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.booksalonappointment.databinding.ActivityBookAppointmentBinding
import com.example.booksalonappointment.databinding.ActivityServiceBinding
import com.example.booksalonappointment.model.Repo.Repository
import com.example.booksalonappointment.model.remote.APIService
import com.example.booksalonappointment.model.remote.response.getbarber.GetBarber
import com.example.booksalonappointment.model.remote.response.service.ServiceCategoryResponse
import com.example.booksalonappointment.view.adapter.GetBarberAdapter
import com.example.booksalonappointment.view.adapter.ServiceAdapter
import com.example.booksalonappointment.viewmodel.getbarber.GetBarberViewModel
import com.example.booksalonappointment.viewmodel.getbarber.GetBarberViewModelFactory
import com.example.booksalonappointment.viewmodel.services.ServiceViewModel
import com.example.booksalonappointment.viewmodel.services.ServiceViewModelFactory

class BookAppointmentActivity : AppCompatActivity() {


    lateinit var binding: ActivityBookAppointmentBinding
    lateinit var viewModel: GetBarberViewModel
    lateinit  var factory: GetBarberViewModelFactory
    lateinit var barberadapter: GetBarberAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookAppointmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewModel()
        binding.apply {
            backService.setOnClickListener {
                super.onBackPressed()
                finish()
            }
        }

    }
    private fun setupViewModel() {
        factory = GetBarberViewModelFactory(Repository(APIService.getInstance()))
        viewModel = ViewModelProvider(this, factory)[GetBarberViewModel::class.java]
        viewModel.getService()
        viewModel.getbarber.observe(this)
        {
            setUpData(it)
        }
    }

    private fun setUpData(barber: GetBarber) {
        barberadapter= GetBarberAdapter(this,barber,viewModel)
        binding.servicerecyclerview.layoutManager= GridLayoutManager(this@BookAppointmentActivity,1)
        binding.servicerecyclerview.adapter=barberadapter
    }
}