package com.example.booksalonappointment.model

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.example.booksalonappointment.model.remote.APIClient
import com.example.booksalonappointment.model.remote.APIService
import com.example.booksalonappointment.model.remote.request.LogInRequest
import com.example.booksalonappointment.model.remote.request.LogOutRequest
import com.example.booksalonappointment.model.remote.request.RegistrationRequest
import com.example.booksalonappointment.model.remote.response.LogInResponse
import com.example.booksalonappointment.model.remote.response.LogOutResponse
import com.example.booksalonappointment.model.remote.response.RegistrationResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository () {
    private val retrofit = APIClient.retrofit
    private val apiService = retrofit.create(APIService::class.java)

    val isProcessing = ObservableField<Boolean>()
    val error = MutableLiveData<String>()
    val registrationResponse = MutableLiveData<RegistrationResponse>()
    val logInResponse=MutableLiveData<LogInResponse>()


    fun register(registrationRequest: RegistrationRequest) {
        isProcessing.set(true)
        val call = apiService.signUpUser(registrationRequest)

        call.enqueue(object : Callback<RegistrationResponse> {
            override fun onResponse(
                call: Call<RegistrationResponse>,
                response: Response<RegistrationResponse>
            ) {
                isProcessing.set(false)
                if(!response.isSuccessful) {
                    error.postValue("Failed to login. Error code: ${response.code()}")
                    return
                }

                val apiResponse = response.body()
                if(apiResponse == null) {
                    error.postValue("Empty response. Please retry.")
                    return
                }
                if(apiResponse.status == 0) {
                    registrationResponse.postValue(apiResponse)
                } else {
                    error.postValue(apiResponse.message)
                }
            }

            override fun onFailure(call: Call<RegistrationResponse>, t: Throwable) {
                isProcessing.set(false)
                t.printStackTrace()
                error.postValue("Error is : ${t.toString()}.\n\nPlease retry.")
            }

        })
    }

    fun login(loginRequest: LogInRequest,header: String) {
        isProcessing.set(true)
        val call = apiService.logInUser(header,loginRequest)

        call.enqueue(object : Callback<LogInResponse> {
            override fun onResponse(call: Call<LogInResponse>, response: Response<LogInResponse>) {
                isProcessing.set(false)
                if(!response.isSuccessful) {
                    error.postValue("Failed to login.")
                } else {
                    val apiResponse = response.body()
                    if (apiResponse == null) {
                        error.postValue("Please retry.")
                    } else {
                        if (apiResponse.status == 0) {
                            logInResponse.postValue(apiResponse)
                        } else {
                            error.postValue(apiResponse.message)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<LogInResponse>, t: Throwable) {
                isProcessing.set(false)
                t.printStackTrace()
                error.postValue("Error message is : ${t.toString()}.\n\nPlease retry.")
            }

        })
    }

    fun logout(userId: String, header: String) {
        isProcessing.set(true)
        val call = apiService.logOutUser(header,userId)

        call.enqueue(object : Callback<LogOutResponse> {
            override fun onResponse(call: Call<LogOutResponse>, response: Response<LogOutResponse>) {
                isProcessing.set(false)
                if(!response.isSuccessful) {
                    error.postValue("Failed to logout.")
                } else {
                    val apiResponse = response.body()
                    if (apiResponse == null) {
                        error.postValue("Please retry.")
                    } else {
                        if (apiResponse.status == 0) {

                        } else {
                            error.postValue(apiResponse.message)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<LogOutResponse>, t: Throwable) {
                isProcessing.set(false)
                t.printStackTrace()
                error.postValue("Error message is : ${t.toString()}.\n\nPlease retry.")
            }

        })
    }

}