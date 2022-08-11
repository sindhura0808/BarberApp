package com.example.booksalonappointment.model.remote.response.time

data class Slot(val date: String,
                val day: String,
                val slots: Map<String, Boolean>)
