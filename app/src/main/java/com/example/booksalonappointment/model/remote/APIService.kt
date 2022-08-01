package com.example.booksalonappointment.model.remote

import com.example.booksalonappointment.model.remote.request.RegistrationRequest
import com.example.booksalonappointment.model.remote.request.LogInRequest
import com.example.booksalonappointment.model.remote.request.LogOutRequest
import com.example.booksalonappointment.model.remote.response.RegistrationResponse
import com.example.booksalonappointment.model.remote.response.LogInResponse
import com.example.booksalonappointment.model.remote.response.LogOutResponse
import com.example.booksalonappointment.model.remote.response.ServiceResponse
import io.reactivex.Single
import retrofit2.Call
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


}