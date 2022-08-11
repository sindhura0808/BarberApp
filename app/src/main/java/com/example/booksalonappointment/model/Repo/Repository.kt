package com.example.booksalonappointment.model.Repo

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.example.booksalonappointment.model.remote.APIClient
import com.example.booksalonappointment.model.remote.APIService
import com.example.booksalonappointment.model.remote.request.LogInRequest
import com.example.booksalonappointment.model.remote.request.RegistrationRequest
import com.example.booksalonappointment.model.remote.response.*
import com.example.booksalonappointment.model.remote.response.getbarber.GetBarber
import com.example.booksalonappointment.model.remote.response.service.ServiceCategoryResponse
import com.example.booksalonappointment.model.remote.response.service.ServiceResponse
import com.example.booksalonappointment.model.remote.response.time.CurrentAppointmentResponse
import com.example.booksalonappointment.model.remote.response.time.Slot
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository (private val apiService: APIService) {


    private val retrofit = APIClient.retrofit
    var compositeDisposable: CompositeDisposable = CompositeDisposable()
    val isProcessing = ObservableField<Boolean>()
    val error = MutableLiveData<String>()
    val registrationResponse = MutableLiveData<RegistrationResponse>()
    val logInResponse=MutableLiveData<LogInResponse>()
    val serviceResponse = MutableLiveData<ServiceResponse>()
    val serviceCategoryResponse = MutableLiveData<ServiceCategoryResponse>()
    val getbarber=MutableLiveData<GetBarber>()
    val barberServiceLiveData = MutableLiveData<BarberServiceResponse>()
    val barberServicesIdLiveData = MutableLiveData<Int>()
    val currentAppointmentsLiveData = MutableLiveData<CurrentAppointmentResponse>()
    val appointmentsDateLiveData = MutableLiveData<String>()
    val appointmentsStartFromLiveData = MutableLiveData<Int>()
    val barbersResponse = MutableLiveData<BarbersResponse>()
    val appointmentResponse = MutableLiveData<AppointmentResponse>()
    val appointmentError = MutableLiveData<String>()
    val appointmentProcessing = MutableLiveData<Boolean>()
    val userLiveData = MutableLiveData<LogInResponse>()


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
                    error.postValue("Failed to register. Error code: ${response.code()}")
                    return
                }

                val apiResponse = response.body()
                if(apiResponse == null) {
                    error.postValue("Please retry!!.")
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

    fun updateFcmToken (fcmToken: String) {
        val ps_auth_token = userLiveData.value!!.apiToken
        val map = HashMap<String, Any>()
        map["userId"] = userLiveData.value!!.userId
        map["fcmToken"] = fcmToken
        map["application"] = "Ray"
        val reqJson: String = Gson().toJson(map)
        val body: RequestBody =
            reqJson.toRequestBody("application/json".toMediaTypeOrNull())
        compositeDisposable.add(apiService.updateFcmToken(ps_auth_token, body)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {

                },
                { t: Throwable? -> Log.i("Throwable", t?.message ?: "error") }
            )

        )
    }

    fun login(loginRequest: LogInRequest) {
        isProcessing.set(true)
        val call = apiService.logInUser(loginRequest)

        call.enqueue(object : Callback<LogInResponse> {
            override fun onResponse(call: Call<LogInResponse>, response: Response<LogInResponse>) {
                isProcessing.set(false)
                if(!response.isSuccessful) {
                    error.postValue("Failed to login. Error code: ${response.code()}")
                } else {
                    val apiResponse = response.body()
                    if (apiResponse == null) {
                        error.postValue("Please retry!!.")
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

    fun logout(userId: String) {
        isProcessing.set(true)
        val call = apiService.logOutUser(userId)

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
    fun bookAppointment(params: HashMap<String, String>) {
        isProcessing.set(true)
        appointmentProcessing.postValue(true)
        val call = apiService.bookAppointment(params)
        call.enqueue(object : Callback<AppointmentResponse> {
            override fun onResponse(
                call: Call<AppointmentResponse>,
                response: Response<AppointmentResponse>
            ) {
                isProcessing.set(false)
                appointmentProcessing.postValue(false)
                if(!response.isSuccessful) {
                    appointmentError.postValue("Failed to book. Error code: ${response.code()}")
                } else {
                    val apiResponse = response.body()
                    if (apiResponse == null) {
                        appointmentError.postValue("Empty response. Please retry.")
                    } else {
                        if (apiResponse.status == 0) {
                            appointmentResponse.postValue(apiResponse)
                        } else {
                            appointmentError.postValue(apiResponse.message)
                        }
                    }
                }
            }
            override fun onFailure(call: Call<AppointmentResponse>, t: Throwable) {
                isProcessing.set(false)
                appointmentProcessing.postValue(false)
                t.printStackTrace()
                appointmentError.postValue("Error is : ${t.toString()}.\n\nPlease retry.")
            }

        })
    }


    fun getBarbers() {
        isProcessing.set(true)
        val call = apiService.getBarbers()
        call.enqueue(object : Callback<BarbersResponse> {
            override fun onResponse(
                call: Call<BarbersResponse>,
                response: Response<BarbersResponse>
            ) {
                isProcessing.set(false)
                if(!response.isSuccessful) {
                    error.postValue("Failed to login. Error code: ${response.code()}")
                } else {
                    val apiResponse = response.body()
                    if (apiResponse == null) {
                        error.postValue("Empty response. Please retry.")
                    } else {
                        if (apiResponse.status == 0) {
                            barbersResponse.postValue(apiResponse)
                        } else {
                            error.postValue(apiResponse.message)
                        }
                    }
                }
            }
            override fun onFailure(call: Call<BarbersResponse>, t: Throwable) {
                isProcessing.set(false)
                t.printStackTrace()
                error.postValue("Error is : ${t.toString()}.\n\nPlease retry.")
            }

        })
    }


    fun getServices() {
        isProcessing.set(true)
        val call = apiService.getServiceCategory()
        call.enqueue(object : Callback<ServiceCategoryResponse> {
            override fun onResponse(
                call: Call<ServiceCategoryResponse>,
                response: Response<ServiceCategoryResponse>
            ) {
                isProcessing.set(false)
                if(!response.isSuccessful) {
                    error.postValue("Failed to login. Error code: ${response.code()}")
                } else {
                    val apiResponse = response.body()
                    if (apiResponse == null) {
                        error.postValue("Empty response. Please retry.")
                    } else {
                        if (apiResponse.status == 0) {
                            serviceCategoryResponse.postValue(apiResponse)
                        } else {
                            error.postValue(apiResponse.message)
                        }
                    }
                }
            }
            override fun onFailure(call: Call<ServiceCategoryResponse>, t: Throwable) {
                isProcessing.set(false)
                t.printStackTrace()
                error.postValue("Error is : ${t.toString()}.\n\nPlease retry.")
            }

        })
    }

    fun getServicesByCategory(catID: String) {
        isProcessing.set(true)
        val call = apiService.getSelectedServiceCategory(catID)
        call.enqueue(object : Callback<ServiceResponse> {
            override fun onResponse(
                call: Call<ServiceResponse>,
                response: Response<ServiceResponse>
            ) {
                isProcessing.set(false)
                if(!response.isSuccessful) {
                    error.postValue("Failed to login. Error code: ${response.code()}")
                } else {
                    val apiResponse = response.body()
                    if (apiResponse == null) {
                        error.postValue("Empty response. Please retry.")
                    } else {
                        if (apiResponse.status == 0) {
                            serviceResponse.postValue(apiResponse)
                        } else {
                            error.postValue(apiResponse.message)
                        }
                    }
                }
            }
            override fun onFailure(call: Call<ServiceResponse>, t: Throwable) {
                isProcessing.set(false)
                t.printStackTrace()
                error.postValue("Error is : ${t.toString()}.\n\nPlease retry.")
            }

        })
    }

    fun getBarber(){

        isProcessing.set(true)
        val call = apiService.getBarber()
        call.enqueue(object : Callback<GetBarber> {
            override fun onResponse(
                call: Call<GetBarber>,
                response: Response<GetBarber>
            ) {
                isProcessing.set(false)
                if(!response.isSuccessful) {
                    error.postValue("Failed to login. Error code: ${response.code()}")
                } else {
                    val apiResponse = response.body()
                    if (apiResponse == null) {
                        error.postValue("Empty response. Please retry.")
                    } else {
                        if (apiResponse.status == 0) {
                         getbarber.postValue(apiResponse)
                        } else {
                            error.postValue(apiResponse.message)
                        }
                    }
                }
            }
            override fun onFailure(call: Call<GetBarber>, t: Throwable) {
                isProcessing.set(false)
                t.printStackTrace()
                error.postValue("Error is : ${t.toString()}.\n\nPlease retry.")
            }

        })

    }
    fun getBarberServices() {
        isProcessing.set(true)

        val map = HashMap<String, String>()
        map["services"] = "services"
        val reqJson: String = Gson().toJson(map)
        val body: RequestBody =
            reqJson.toRequestBody("application/json".toMediaTypeOrNull())

        val call: Call<BarberServiceResponse> = apiService.getBarberServices(body)
        call.enqueue(object : Callback<BarberServiceResponse> {
            override fun onResponse(call: Call<BarberServiceResponse>, response: Response<BarberServiceResponse>) {
                if (response.isSuccessful) {
                    if(response.body()!!.status == 0){
                        barberServiceLiveData.postValue(response.body()!!)
                    } else {
                        Log.e("response error", response.body()!!.message)
                    }
                }
            }
            override fun onFailure(call: Call<BarberServiceResponse>, t: Throwable) {
                Log.e("TAG", t.toString())
                t.printStackTrace()
            }
        })
    }

    fun loadCurrentAppointments() {
        val call: Call<CurrentAppointmentResponse> = apiService.currentAppointments()
        call.enqueue(object : Callback<CurrentAppointmentResponse> {
            override fun onResponse(
                call: Call<CurrentAppointmentResponse>,
                response: Response<CurrentAppointmentResponse>
            ) {
                if (response.isSuccessful) {
                    if (response.body()!!.status == 0) {
                        Log.e("loadCurrentAppointments", response.body()!!.slots.toString())
                        currentAppointmentsLiveData.postValue(response.body()!!)

                    } else {
                        Log.e("response error", response.body()!!.message)
                    }
                }
            }

            override fun onFailure(call: Call<CurrentAppointmentResponse>, t: Throwable) {
                Log.e("response.body()", t.toString())
                t.printStackTrace()
            }
        })
    }


}