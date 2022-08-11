package com.example.booksalonappointment.viewmodel.timeselection

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksalonappointment.model.Repo.Repository
import com.example.booksalonappointment.model.remote.response.time.Slot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TimeSelectionViewModel (private val repository: Repository) : ViewModel() {


    val currentAppointmentsResponse = repository.currentAppointmentsLiveData
    val appointmentsDateLiveData = MutableLiveData<String>()
    val appointmentsSlotLiveData = MutableLiveData<Int>()
    val appointmentsStartFromLiveData = MutableLiveData<Int>()
    val currentAppointmentsLiveData = MutableLiveData<ArrayList<Slot>>()


    fun loadAppointmentTime() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.loadCurrentAppointments()
        }
    }

    fun setAppointmentsDate(date: String) {
        repository.appointmentsDateLiveData.postValue(date)
    }
    fun setAppointmentsStartFrom(startFrom: Int) {
        repository.appointmentsStartFromLiveData.postValue(startFrom)
    }



}