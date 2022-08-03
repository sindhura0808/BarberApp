package com.example.booksalonappointment.model.remote.response

import com.google.gson.annotations.SerializedName

data class ServiceResponse(

    @SerializedName("services")
    val services: Services,

    @SerializedName("status")
    val status: Int
)