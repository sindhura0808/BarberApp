package com.example.booksalonappointment.model.remote.request

data class RegistrationRequest(
    val fcmToken: String,
    val mobileNo: String,
    val password: String
)