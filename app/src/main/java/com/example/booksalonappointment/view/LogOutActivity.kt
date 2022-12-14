package com.example.booksalonappointment.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.booksalonappointment.R
import com.example.booksalonappointment.model.Repo.Repository
import com.example.booksalonappointment.model.remote.APIService
import com.example.booksalonappointment.viewmodel.logout.LogoutViewModel
import com.example.booksalonappointment.viewmodel.logout.LogoutViewModelFactory

class LogOutActivity : AppCompatActivity() {

    private lateinit var viewModel: LogoutViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_out)
        setupViewModel()
        val userid = getUserID()
        val fcmToken = getfcmToken()
        viewModel.onLogoutClick(userid!!)
        deleteSharedpreferencedata()
        Handler().postDelayed({
            var intent= Intent(this, LogInActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)

    }

    private fun deleteSharedpreferencedata() {
        val pref = getSharedPreferences("users", MODE_PRIVATE)
        pref.edit().clear().commit()
    }

    private fun setupViewModel() {
        val viewmodelFactory = LogoutViewModelFactory(Repository(APIService.getInstance()))
        viewModel = ViewModelProvider(this, viewmodelFactory)[LogoutViewModel::class.java]
    }

    private fun getfcmToken():String? {
        val pref = getSharedPreferences("users", MODE_PRIVATE)
        return pref.getString("fcm_Token", " ")
    }

    private fun getUserID(): String? {
        val pref = getSharedPreferences("users", MODE_PRIVATE)
        return pref.getString("user_id", " ")

    }


}