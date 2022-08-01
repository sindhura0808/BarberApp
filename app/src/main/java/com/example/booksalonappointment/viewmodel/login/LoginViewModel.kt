package com.example.booksalonappointment.viewmodel.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.booksalonappointment.model.Repository
import com.example.booksalonappointment.model.remote.request.LogInRequest
import com.example.booksalonappointment.model.remote.response.LogInResponse

class LoginViewModel (private val repository: Repository): ViewModel() {
    val mobileNumber = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val fcmToken = MutableLiveData<String>()

    val loginResponse: LiveData<LogInResponse> = repository.logInResponse
    val error: LiveData<String> = repository.error
    val isProcessing = repository.isProcessing

    fun onLoginClick() {
        val loginRequest = LogInRequest(mobileNumber.value!!, password.value!!)
        repository.login(loginRequest, fcmToken.value!!)
    }
}