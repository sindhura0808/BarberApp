package com.example.booksalonappointment.viewmodel.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.booksalonappointment.model.Repo.Repository
import com.example.booksalonappointment.model.remote.request.LogInRequest
import com.example.booksalonappointment.model.remote.response.LogInResponse
import com.example.booksalonappointment.model.util.isMobileValid
import com.example.booksalonappointment.model.util.isNotEmpty
import com.example.booksalonappointment.model.util.isPasswordValid
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

class LoginViewModel (private val repository: Repository): ViewModel() {
    val mobileNumber = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val fcmToken = MutableLiveData<String>()
    val loginResponse: LiveData<LogInResponse> = repository.logInResponse
    val error: LiveData<String> = repository.error


    fun onLogin(mobileNumber: String, password: String) {

            val loginRequest = LogInRequest(mobileNumber,password)
            repository.login(loginRequest)
        }

  fun updateFCM(fcmToken:String){
      repository.updateFcmToken(fcmToken)
  }

    }


