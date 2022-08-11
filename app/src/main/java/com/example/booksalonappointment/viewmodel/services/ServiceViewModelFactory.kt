package com.example.booksalonappointment.viewmodel.services

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.booksalonappointment.model.Repo.Repository
import java.lang.IllegalArgumentException

class ServiceViewModelFactory constructor(private val repository: Repository) :
    ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(ServiceViewModel::class.java)) {
            ServiceViewModel(this.repository) as T
        }
        else
        {
            throw IllegalArgumentException("View Model not found!!")
        }
    }
}