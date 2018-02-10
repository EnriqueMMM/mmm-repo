package com.app.test.testapp.view.activities

import android.Manifest
import android.annotation.SuppressLint
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity

@SuppressLint("Registered")
/**
 * Created by Enrique on 2/10/2018.
 */
open class BaseActivity : AppCompatActivity() {

    companion object {
        const val ACCESS_COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION
        const val ACCESS_FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION
        val perRequestCode = 333
    }
    protected fun checkForAppPermissions(permissionsList: Array<String>, requestCode: Int, mustRequest: Boolean): Boolean {
        var permissionValue = 0
        permissionsList.forEach {
            permissionValue += ContextCompat.checkSelfPermission(this, it)
        }
        if (permissionValue != 0 && mustRequest) {
            ActivityCompat.requestPermissions(this, permissionsList, requestCode)
        }

        return permissionValue == 0
    }
}