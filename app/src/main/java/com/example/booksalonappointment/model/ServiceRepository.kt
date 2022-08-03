package com.example.booksalonappointment.model

import com.example.booksalonappointment.model.remote.APIService

class ServiceRepository (private val apiService: APIService) {

    fun getServices()=apiService.getServices()
}