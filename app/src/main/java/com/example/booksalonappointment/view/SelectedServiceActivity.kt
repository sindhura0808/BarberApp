package com.example.booksalonappointment.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.booksalonappointment.R
import com.example.booksalonappointment.databinding.ActivitySelectedServiceBinding
import com.example.booksalonappointment.databinding.ActivityShareBinding
import com.example.booksalonappointment.model.Repo.Repository
import com.example.booksalonappointment.model.remote.APIService
import com.example.booksalonappointment.model.remote.response.service.ServiceCategoryResponse
import com.example.booksalonappointment.model.remote.response.service.ServiceResponse
import com.example.booksalonappointment.view.adapter.SelectedServicesAdapter
import com.example.booksalonappointment.view.adapter.ServiceAdapter
import com.example.booksalonappointment.viewmodel.Selectedservice.SelectedServicesViewModel
import com.example.booksalonappointment.viewmodel.Selectedservice.SelectedServicesViewModelFactory
import com.example.booksalonappointment.viewmodel.services.ServiceViewModel
import com.example.booksalonappointment.viewmodel.services.ServiceViewModelFactory

class SelectedServiceActivity : AppCompatActivity() {

    lateinit var binding: ActivitySelectedServiceBinding
    lateinit var viewModel: SelectedServicesViewModel
    lateinit  var factory: SelectedServicesViewModelFactory
    lateinit var serviceadapter: SelectedServicesAdapter
    lateinit var id:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectedServiceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var intent: Intent = getIntent()
         id = intent.getStringExtra("catID").toString()
        setupViewModel()
        binding.apply {
            backService.setOnClickListener {
                super.onBackPressed()
                finish()
            }
        }
    }

    private fun setupViewModel() {
        factory = SelectedServicesViewModelFactory(Repository(APIService.getInstance()))
        viewModel = ViewModelProvider(this, factory)[SelectedServicesViewModel::class.java]
        viewModel.getSelectedService(id)
        viewModel.serviceResponse.observe(this)
        {
            setUpData(it)
        }
    }

    private fun setUpData(service: ServiceResponse) {
        serviceadapter= SelectedServicesAdapter(this,service)
        binding.selectedServicesRecyclerView.layoutManager= GridLayoutManager(this@SelectedServiceActivity,1)
        binding.selectedServicesRecyclerView.adapter=serviceadapter
    }
}