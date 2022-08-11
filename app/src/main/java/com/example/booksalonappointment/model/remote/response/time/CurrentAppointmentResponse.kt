package com.example.booksalonappointment.model.remote.response.time

data class CurrentAppointmentResponse(
    val message: String,
    val slots: ArrayList<Slot>,
    val status: Int
)
