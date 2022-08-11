package com.example.booksalonappointment.model.remote.response.getbarber

data class GetBarber(
    val barbers: List<Barber>,
    val message: String,
    val status: Int
)