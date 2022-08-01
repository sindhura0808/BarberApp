package com.example.booksalonappointment.model.remote

import com.example.booksalonappointment.model.remote.Constants.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

import retrofit2.converter.gson.GsonConverterFactory

object APIClient {
    val retrofit by lazy {
        val logInterceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

        val client = OkHttpClient.Builder().apply {
            addInterceptor(logInterceptor)
        }.build()

        val retrofit = Retrofit.Builder().apply {
            baseUrl(BASE_URL)
            addConverterFactory(GsonConverterFactory.create())
            client(client)
        }.build()

        retrofit  // return Retrofit object
    }
}