package com.example.booksalonappointment.viewmodel.logout

import androidx.lifecycle.ViewModel
import com.example.booksalonappointment.model.Repo.Repository

class LogoutViewModel (private val repository: Repository): ViewModel() {
  //  val userId=MutableLiveData<String>()
    fun onLogoutClick(userId:String) {
      repository.logout(userId)
    }
}