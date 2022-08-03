package com.example.booksalonappointment.viewmodel.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.booksalonappointment.model.Repository
import com.example.booksalonappointment.model.remote.request.LogInRequest
import com.example.booksalonappointment.model.remote.response.LogInResponse
import com.example.booksalonappointment.model.util.isMobileValid
import com.example.booksalonappointment.model.util.isNotEmpty
import com.example.booksalonappointment.model.util.isPasswordValid

class LoginViewModel (private val repository: Repository): ViewModel() {
    val mobileNumber = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val fcmToken = MutableLiveData<String>()

    val mError = MutableLiveData<String>()
    val passError = MutableLiveData<String>()

    val loginResponse: LiveData<LogInResponse> = repository.logInResponse
    val error: LiveData<String> = repository.error

    fun onLoginClick() {

        if(validateLogInInput()) {
            val loginRequest = LogInRequest(mobileNumber.value!!, password.value!!)
            repository.login(loginRequest, fcmToken.value!!)
        }
    }

    private fun validateLogInInput(): Boolean {
        mError.value= if(isNotEmpty(mobileNumber.value)) null else "Required"
        passError.value= if(isNotEmpty(password.value)) null else "Required"
        if (!(isNotEmpty(mobileNumber.value) && isNotEmpty(password.value))) {
            return false
        }
        mError.value = if (isMobileValid(mobileNumber.value.toString())) null else "Invalid Number"
        passError.value = if (isPasswordValid(password.value.toString())) null else "Password should be at least 8 characters"

        return isNotEmpty(mobileNumber.value) &&
                isNotEmpty(password.value) &&
                isMobileValid(mobileNumber.value.toString()) &&
                isPasswordValid(password.value.toString())
    }
}