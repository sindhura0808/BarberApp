package com.example.booksalonappointment.viewmodel.logout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.booksalonappointment.model.Repo.Repository

class LogoutViewModelFactory(val repository: Repository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LogoutViewModel(repository) as T
    }
}