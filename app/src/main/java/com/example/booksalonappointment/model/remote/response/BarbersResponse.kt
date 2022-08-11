package com.example.booksalonappointment.model.remote.response

import com.example.booksalonappointment.model.remote.response.getbarber.Barber

data class BarbersResponse(
    val barbers: List<Barber>,
    val message: String,
    val status: Int
)
