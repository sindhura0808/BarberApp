package com.example.booksalonappointment.model.remote.request

import com.google.gson.annotations.SerializedName

data class LogInRequest (
    val mobileNo: String,
    val password: String,
)