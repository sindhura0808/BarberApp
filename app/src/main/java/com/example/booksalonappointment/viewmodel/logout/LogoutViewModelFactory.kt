package com.example.booksalonappointment.viewmodel.logout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.booksalonappointment.model.Repository
import com.example.booksalonappointment.viewmodel.login.LoginViewModel

class LogoutViewModelFactory(val repository: Repository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LogoutViewModel(repository) as T
    }
}