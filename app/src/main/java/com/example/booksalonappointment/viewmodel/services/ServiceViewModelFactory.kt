package com.example.booksalonappointment.viewmodel.services

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.booksalonappointment.model.Repository
import com.example.booksalonappointment.model.ServiceRepository
import com.example.booksalonappointment.viewmodel.login.LoginViewModel

class ServiceViewModelFactory (val repository: ServiceRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ServiceViewModel(repository) as T
    }
}
