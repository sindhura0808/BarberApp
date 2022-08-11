package com.example.booksalonappointment.view

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import com.example.booksalonappointment.R
import com.example.booksalonappointment.databinding.ActivityLocation2Binding
import com.example.booksalonappointment.model.util.hasPermission
import com.example.booksalonappointment.model.util.requiredPermissionWithRationale
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar

class LocationActivity : AppCompatActivity(){


    private lateinit var binding: ActivityLocation2Binding
    private var cancellationTokenSource= CancellationTokenSource()

    private val fusedLocationProviderClient: FusedLocationProviderClient by lazy {

        LocationServices.getFusedLocationProviderClient(applicationContext)
    }
    private val fineLocationRationaleSnackBar by lazy {
        Snackbar.make(binding.map, R.string.fine_location_permission, Snackbar.LENGTH_LONG)
            .setAction(R.string.ok) {
                requestPermissions(
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    REQUEST_CODE_LOCATION_PERMISSION
                )
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLocation2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnFetchLocation.setOnClickListener{
            requestForLocation()
        }
    }

    private fun requestForLocation() {
        val permissionApproved = applicationContext.hasPermission(Manifest.permission.ACCESS_FINE_LOCATION)
        if(permissionApproved)
        {
            requestCurrentLocation()
        }else {
            requiredPermissionWithRationale(
                Manifest.permission.ACCESS_FINE_LOCATION,
                REQUEST_CODE_LOCATION_PERMISSION,
                fineLocationRationaleSnackBar
            )
        }
    }

    @SuppressLint("MissingPermission", "SetTextI18n")
    private fun requestCurrentLocation() {
        if (applicationContext.hasPermission(Manifest.permission.ACCESS_FINE_LOCATION)) {
            val currentTask: Task<Location> = fusedLocationProviderClient.getCurrentLocation(
                Priority.PRIORITY_HIGH_ACCURACY, cancellationTokenSource.token
            )

            currentTask.addOnCompleteListener { task: Task<Location> ->
                if (task.isSuccessful && task.result != null) {
                    val result: Location = task.result
                    val intent = Intent(this, MapActivity::class.java)
                    intent.putExtra("lat", result.latitude)
                    intent.putExtra("long", result.longitude)
                    startActivity(intent)
                }
            }
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode==REQUEST_CODE_LOCATION_PERMISSION)
            when{
                grantResults.isEmpty() ->
                    Log.i("tag" , "User interaction is cancelled")

                grantResults[0] == PackageManager.PERMISSION_GRANTED ->
                    Snackbar.make(binding.map, R.string.permission_approved, Snackbar.LENGTH_LONG).show()

            }
        else
        {
            Snackbar.make(binding.map, R.string.permission_denied, Snackbar.LENGTH_LONG)
                .setAction(R.string.setting)
                {
                    val intent= Intent()
                    intent.action= Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                    val uri= Uri.fromParts("package", "com.example.locationservicedemo", null)
                    intent.data= uri
                    intent.flags= Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)

                }.show()
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    companion object{
        private const val REQUEST_CODE_LOCATION_PERMISSION=100
    }
}