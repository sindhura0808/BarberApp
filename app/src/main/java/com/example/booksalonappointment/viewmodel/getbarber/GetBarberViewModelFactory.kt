package com.example.booksalonappointment.viewmodel.getbarber

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.booksalonappointment.model.Repo.Repository
import java.lang.IllegalArgumentException

class GetBarberViewModelFactory constructor(private val repository: Repository) :
    ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(  GetBarberViewModel::class.java)) {
            GetBarberViewModel(this.repository) as T
        }
        else
        {
            throw IllegalArgumentException("View Model not found!!")
        }
    }
}