package com.example.booksalonappointment.viewmodel.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.booksalonappointment.model.Repo.Repository
import com.example.booksalonappointment.model.remote.request.RegistrationRequest
import com.example.booksalonappointment.model.remote.response.RegistrationResponse
import com.example.booksalonappointment.model.util.confirmPassword
import com.example.booksalonappointment.model.util.isMobileValid
import com.example.booksalonappointment.model.util.isNotEmpty

class RegistrationViewModel(private val repository: Repository) : ViewModel() {
    val mobileNumber = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val confirmPassword = MutableLiveData<String>()
    val fcmToken = MutableLiveData<String>()
    var userLiveData = repository.registrationResponse


    val error: LiveData<String> = repository.error

    fun onRegisterClick(mobileNo: String, password: String, fcmToken: String) {
        val registrationRequest = RegistrationRequest(fcmToken,mobileNo,password)
            repository.register(registrationRequest)
        }
    }