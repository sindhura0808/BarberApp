package com.example.booksalonappointment.model.remote.response

data class BarberServiceResponse(
    val message: String,
    val services: ArrayList<BarberService>,
    val status: Int
)