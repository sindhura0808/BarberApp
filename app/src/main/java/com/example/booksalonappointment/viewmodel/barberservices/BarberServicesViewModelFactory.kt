package com.example.booksalonappointment.viewmodel.barberservices

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.booksalonappointment.model.Repo.Repository

@Suppress("UNCHECKED_CAST")
class BarberServicesVMFactory(private val repository: Repository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BarberServicesViewModel(repository) as T
    }
}