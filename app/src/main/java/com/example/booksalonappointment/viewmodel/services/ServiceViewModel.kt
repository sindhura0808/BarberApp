package com.example.booksalonappointment.viewmodel.services

import android.app.Service
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.booksalonappointment.model.Repository
import com.example.booksalonappointment.model.ServiceRepository
import com.example.booksalonappointment.model.remote.response.ServiceResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class ServiceViewModel(private val repository: ServiceRepository) : ViewModel() {

    val serviceList = MutableLiveData<List<ServiceResponse>>()
    var compositeDisposable : CompositeDisposable = CompositeDisposable()

    fun loadService()
    {
        compositeDisposable.addAll(repository.getServices()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { serviceList.postValue(it) },
                { t: Throwable? ->
                    Log.i("tag", t?.message ?: "error")
                }
            ))
    }
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}

