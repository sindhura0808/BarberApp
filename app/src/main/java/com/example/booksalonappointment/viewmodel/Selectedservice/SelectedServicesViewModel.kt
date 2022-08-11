package com.example.booksalonappointment.viewmodel.Selectedservice

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.booksalonappointment.model.Repo.Repository
import com.example.booksalonappointment.model.remote.response.service.Service

class SelectedServicesViewModel (private val repository: Repository): ViewModel() {
    val serviceResponse = repository.serviceResponse
    val services = MutableLiveData<ArrayList<List<Service>>>()
    val error: MutableLiveData<String> = repository.error
    val isProcessing = repository.isProcessing

    fun getSelectedService(id:String) {
        repository.getServicesByCategory(id)

        val num = serviceResponse.value?.services?.size
        services.value = ArrayList()
//        viewModelScope.launch {
//            for (i in 1..num!!) {
//                try {
//                    repository.getServiceByCategory(i.toString())
//                    services.value!!.add(serviceResponse.value?.services!!)
//                } catch (exception: Exception) {
//                    error.postValue(exception.message)
//                    isProcessing.set(false)
//                }
//            }
//            isProcessing.set(false)
//        }
    }
}