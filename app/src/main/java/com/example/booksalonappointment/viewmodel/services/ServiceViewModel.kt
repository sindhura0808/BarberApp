package com.example.booksalonappointment.viewmodel.services


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.booksalonappointment.model.Repo.Repository
import com.example.booksalonappointment.model.remote.response.service.Service


class ServiceViewModel(private val repository: Repository): ViewModel() {
    val serviceCategoryResponse = repository.serviceCategoryResponse
    val serviceResponse = repository.serviceResponse
    val services = MutableLiveData<ArrayList<List<Service>>>()
    val error: MutableLiveData<String> = repository.error
    val isProcessing = repository.isProcessing

    fun getService() {
        repository.getServices()

        val num = serviceCategoryResponse.value?.serviceCategories?.size
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