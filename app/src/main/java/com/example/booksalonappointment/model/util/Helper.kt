package com.example.booksalonappointment.model.util

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import com.google.android.material.snackbar.Snackbar

fun Context.hasPermission(permission:String): Boolean {
    if(permission == Manifest.permission.ACCESS_BACKGROUND_LOCATION && android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.Q) {
        return true
    }
    return ActivityCompat.checkSelfPermission(this,permission) == PackageManager.PERMISSION_GRANTED
}

@RequiresApi(Build.VERSION_CODES.M)
fun Activity.requiredPermissionWithRationale(
    permission:String,
    requestCode:Int,
    snackBar: Snackbar
){
    val provideRationale = shouldShowRequestPermissionRationale(permission)
    if (provideRationale) {
        snackBar.show()
    } else {
        requestPermissions(arrayOf(permission), requestCode)
    }
}