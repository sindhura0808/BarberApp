package com.example.booksalonappointment.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.booksalonappointment.databinding.ActivityServiceBinding
import com.example.booksalonappointment.model.Repo.Repository
import com.example.booksalonappointment.model.remote.APIService
import com.example.booksalonappointment.model.remote.response.service.ServiceCategoryResponse
import com.example.booksalonappointment.model.remote.response.service.ServiceResponse
import com.example.booksalonappointment.view.adapter.ServiceAdapter
import com.example.booksalonappointment.viewmodel.services.ServiceViewModel
import com.example.booksalonappointment.viewmodel.services.ServiceViewModelFactory

class ServiceActivity : AppCompatActivity() {

    lateinit var binding: ActivityServiceBinding
    lateinit var viewModel: ServiceViewModel
    lateinit  var factory: ServiceViewModelFactory
    lateinit var serviceadapter: ServiceAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityServiceBinding.inflate(layoutInflater)
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
         factory = ServiceViewModelFactory(Repository(APIService.getInstance()))
        viewModel = ViewModelProvider(this, factory)[ServiceViewModel::class.java]
        viewModel.getService()
        viewModel.serviceCategoryResponse.observe(this)
        {
            setUpData(it)
        }
    }

    private fun setUpData(service: ServiceCategoryResponse) {
        serviceadapter= ServiceAdapter(this,service)
        binding.servicerecyclerview.layoutManager= GridLayoutManager(this@ServiceActivity,2)
        binding.servicerecyclerview.adapter=serviceadapter
    }
}