package com.example.booksalonappointment.viewmodel.logout

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.booksalonappointment.model.Repository
import com.example.booksalonappointment.model.remote.request.LogInRequest
import com.example.booksalonappointment.model.remote.request.LogOutRequest

class LogoutViewModel (private val repository: Repository): ViewModel() {
  //  val userId=MutableLiveData<String>()
    fun onLogoutClick( userId:String, fcmToken:String) {
      repository.logout(userId, fcmToken)
    }
}