package com.example.booksalonappointment.viewmodel.getbarber


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksalonappointment.model.Repo.Repository
import com.example.booksalonappointment.model.remote.response.BarberServiceResponse
import com.example.booksalonappointment.model.remote.response.getbarber.Barber
import com.example.booksalonappointment.model.remote.response.service.Service
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class GetBarberViewModel(private val repository: Repository) : ViewModel() {
    val getbarber = repository.getbarber
    val selectedBarber = MutableLiveData<Barber>()
    val selectedServices = MutableLiveData<ArrayList<Service>>()

    val barberServiceResponse: LiveData<BarberServiceResponse> = repository.barberServiceLiveData
    val error: LiveData<String> = repository.error
    val isProcessing = repository.isProcessing

    fun getBarberServices() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getBarberServices()
        }
    }
    fun getService() {
        repository.getBarber()
    }
}