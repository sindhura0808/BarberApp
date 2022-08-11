package com.example.booksalonappointment.model.remote.response

data class BarberService(
    val cost: Double,
    val duration: Double,
    val serviceId: Int,
    val serviceName: String,
    val servicePic: String,
    var isSelected: Boolean
)