package com.example.booksalonappointment.viewmodel.barberservices

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksalonappointment.model.Repo.Repository
import com.example.booksalonappointment.model.remote.response.BarberServiceResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BarberServicesViewModel(private val repository: Repository) : ViewModel() {

    val barberServiceResponse: LiveData<BarberServiceResponse> = repository.barberServiceLiveData
    val error: LiveData<String> = repository.error
    val isProcessing = repository.isProcessing

    fun getBarberServices() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getBarberServices()
        }
    }
}