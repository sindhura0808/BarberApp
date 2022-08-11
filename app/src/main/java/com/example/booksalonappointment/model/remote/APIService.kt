package com.example.booksalonappointment.model.remote

import com.example.booksalonappointment.model.RetrofitClient
import com.example.booksalonappointment.model.remote.request.RegistrationRequest
import com.example.booksalonappointment.model.remote.request.LogInRequest
import com.example.booksalonappointment.model.remote.response.*
import com.example.booksalonappointment.model.remote.response.getbarber.GetBarber
import com.example.booksalonappointment.model.remote.response.service.ServiceCategoryResponse
import com.example.booksalonappointment.model.remote.response.service.ServiceResponse
import com.example.booksalonappointment.model.remote.response.time.CurrentAppointmentResponse
import io.reactivex.Single
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface APIService{
    @Headers("Content-type: application/json")
    @POST("/appUser/signup")
    fun signUpUser(@Body registrationRequest: RegistrationRequest): Call<RegistrationResponse>

    @Headers("Content-type: application/json")
    @POST("/appUser/login")
    fun logInUser(@Body loginRequest: LogInRequest): Call<LogInResponse>

    @Headers("Content-type: application/json")
    @POST("/User/logout")
    fun logOutUser(@Body userId: String): Call<LogOutResponse>


    @GET("/service/getServiceCategory")
    fun getServiceCategory(): Call<ServiceCategoryResponse>

    @GET("/barber/getBarbers")
    fun getBarber(): Call<GetBarber>

    @GET("/service/category/{category_id}")
    fun getSelectedServiceCategory(@Path("category_id") category_is: String) : Call<ServiceResponse>

    @Headers("Content-type: application/json")
    @POST("barber/getBarberServices1")
    fun getBarberServices(@Body getBarberServicesReq: RequestBody): Call<BarberServiceResponse>

    @Headers("Content-type: application/json")
    @GET("appointment/currentAppointments/1" )
    fun currentAppointments(): Call<CurrentAppointmentResponse>
    @Headers("Content-type: application/json")
    @POST("appUser/updateFcmToken")
    fun updateFcmToken(@Header("ps_auth_token") ps_auth_token: String, @Body updateReq: RequestBody): Single<RegistrationResponse>

    @POST("/appointment/book")
    fun bookAppointment(
        @QueryMap params: HashMap<String, String>
    ): Call<AppointmentResponse>

    @GET("/barber/getBarbers")
    fun getBarbers(): Call<BarbersResponse>

    companion object{
        fun getInstance(): APIService =
            RetrofitClient.retrofit.create(APIService::class.java)
    }

}