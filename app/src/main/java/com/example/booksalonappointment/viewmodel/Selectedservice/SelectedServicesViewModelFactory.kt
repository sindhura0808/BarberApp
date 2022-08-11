package com.example.booksalonappointment.viewmodel.Selectedservice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.booksalonappointment.model.Repo.Repository
import com.example.booksalonappointment.viewmodel.services.ServiceViewModel
import java.lang.IllegalArgumentException

class SelectedServicesViewModelFactory constructor(private val repository: Repository) :
    ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(SelectedServicesViewModel::class.java)) {
            SelectedServicesViewModel(this.repository) as T
        }
        else
        {
            throw IllegalArgumentException("View Model not found!!")
        }
    }
}