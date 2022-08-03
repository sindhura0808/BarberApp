package com.example.booksalonappointment.viewmodel.registration

import android.content.SharedPreferences
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.booksalonappointment.model.Repository
import com.example.booksalonappointment.model.remote.request.RegistrationRequest
import com.example.booksalonappointment.model.remote.response.RegistrationResponse
import com.example.booksalonappointment.model.util.confirmPassword
import com.example.booksalonappointment.model.util.isMobileValid
import com.example.booksalonappointment.model.util.isNotEmpty
import com.google.firebase.database.*

class RegistrationViewModel(private val repository: Repository) : ViewModel() {
    val mobileNumber = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val confirmPassword = MutableLiveData<String>()
    val fcmToken = MutableLiveData<String>()

    val isMobileNumberNotEmpty = MutableLiveData<Boolean>()
    val isPasswordNotEmpty = MutableLiveData<Boolean>()
    val isConfirmPasswordNotEmpty = MutableLiveData<Boolean>()
    val isMobileNumberValid = MutableLiveData<Boolean>()
    val matchPassword = MutableLiveData<Boolean>()

    val registrationResponse: LiveData<RegistrationResponse> = repository.registrationResponse
    val error: LiveData<String> = repository.error
    val isProcessing = repository.isProcessing

    fun onRegisterClick() {
        if (validateInput()) {
            val registrationRequest = RegistrationRequest(fcmToken.value!!, mobileNumber.value!!, password.value!!)
            repository.register(registrationRequest)
        }
    }
    private fun validateInput(): Boolean {
        isMobileNumberNotEmpty.value = isNotEmpty(mobileNumber.value)
        isPasswordNotEmpty.value = isNotEmpty(password.value)
        isConfirmPasswordNotEmpty.value = isNotEmpty(confirmPassword.value)

        if(password.value!!.length<8) { return false}

        if (!(isMobileNumberNotEmpty.value!! && isConfirmPasswordNotEmpty.value!! && isPasswordNotEmpty.value!!)) {
            return false
        }

        isMobileNumberValid.value = isMobileValid(mobileNumber.value.toString())
        matchPassword.value = confirmPassword(password.value.toString(), confirmPassword.value.toString())

        return isMobileNumberValid.value!! &&
                isConfirmPasswordNotEmpty.value!! &&
                isMobileNumberNotEmpty.value!! &&
                isPasswordNotEmpty.value!! &&
                matchPassword.value!!
    }

}