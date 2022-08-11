package com.example.booksalonappointment.model.remote.response


data class AppointmentResponse(
    val appointment: Appointment,
    val message: String,
    val status: Int
)