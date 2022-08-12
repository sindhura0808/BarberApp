package com.example.booksalonappointment.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.booksalonappointment.R
import com.example.booksalonappointment.databinding.ActivityBarberServiceBinding
import com.example.booksalonappointment.model.Repo.Repository
import com.example.booksalonappointment.model.remote.APIService
import com.example.booksalonappointment.model.remote.response.BarberService
import com.example.booksalonappointment.view.adapter.BarberServiceAdapter
import com.example.booksalonappointment.viewmodel.barberservices.BarberServicesVMFactory
import com.example.booksalonappointment.viewmodel.barberservices.BarberServicesViewModel
import com.example.booksalonappointment.viewmodel.getbarber.GetBarberViewModel

class BarberServiceActivity : AppCompatActivity() {

    lateinit var binding:ActivityBarberServiceBinding
    private lateinit var barberAdapter: BarberServiceAdapter
    private lateinit var barberServices: ArrayList<BarberService>
    lateinit var viewModel: BarberServicesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
   binding=ActivityBarberServiceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        barberServices = ArrayList()
        setupViewModel()
        setupObservers()
        viewModel.getBarberServices()
        binding.apply {
            backService.setOnClickListener {
                super.onBackPressed()
                finish()
            }
           btnChangeBarber.setOnClickListener(){
                super.onBackPressed()
                finish()
            }

            /*btnContinue.setOnClickListener {
                val selectedServices = ArrayList<BarberService>()
                var selectionCount = 0
                for (b in barberServices) {
                    if (b.isSelected) {
                        selectionCount++
                        selectedServices.add(b)
                    }
                }
                if (selectionCount != 0 && selectedServices.isNotEmpty()) {
                    startActivity(
                        Intent(
                           this@BarberServiceActivity,
                            TimeSelectionActivity::class.java
                        )
                    )
                } else {
                    Toast.makeText(
                        this@BarberServiceActivity,
                        " Please select at least 1 option",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }*/
            btnChangeBarber.setOnClickListener {
                super.onBackPressed()
            }
        }

    }


    private fun setAdapter() {
        barberAdapter = BarberServiceAdapter(this, barberServices)
        binding.apply {
            recyclerViewServices.layoutManager = LinearLayoutManager(this@BarberServiceActivity,)
            recyclerViewServices.adapter = barberAdapter
        }
    }

    private fun setupViewModel() {
        val factory = BarberServicesVMFactory(Repository(APIService.getInstance()))
        viewModel = ViewModelProvider(this, factory)[BarberServicesViewModel::class.java]
        binding.viewModel = viewModel
    }

    private fun setupObservers() {
        viewModel.barberServiceResponse.observe(this) {
            barberServices = it.services
            setAdapter()
            binding.apply {
                recyclerViewServices.visibility = View.VISIBLE
            }
        }

        viewModel.error.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }
}

