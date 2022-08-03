package com.example.booksalonappointment.model.remote

import com.example.booksalonappointment.model.RetrofitClient
import com.example.booksalonappointment.model.remote.request.RegistrationRequest
import com.example.booksalonappointment.model.remote.request.LogInRequest
import com.example.booksalonappointment.model.remote.request.LogOutRequest
import com.example.booksalonappointment.model.remote.response.*
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface APIService{
    @Headers("Content-type: application/json")
    @POST("AppUser/signup")
    fun signUpUser(@Body registrationRequest: RegistrationRequest): Call<RegistrationResponse>

    @Headers("Content-type: application/json")
    @POST("AppUser/login")
    fun logInUser(@Header("ps_auth_token") token: String,
                  @Body loginRequest: LogInRequest): Call<LogInResponse>

    @Headers("Content-type: application/json")
    @POST("User/logout")
    fun logOutUser(@Header("ps_auth_token") token: String,
                  @Body userId: String): Call<LogOutResponse>

    @GET("Service/getServices")
    fun getServices(): Single<List<ServiceResponse>>


    companion object{
        fun getInstance(): APIService =
            RetrofitClient.retrofit.create(APIService::class.java)
    }

}