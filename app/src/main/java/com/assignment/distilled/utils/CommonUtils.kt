package com.assignment.distilled.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


/**
 * Created by Sibaprasad Mohanty on 22/07/21.
 * Spm Limited
 * sp.dobest@gmail.com
 */

class CommonUtils {

    companion object {

        fun checkPermission(applicationContext: Context): Boolean {
            val result = ContextCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            return result == PackageManager.PERMISSION_GRANTED
        }

        fun requestPermission(
            applicationContext: Activity,
            permissionArray: Array<String>,
            PERMISSION_REQUEST_CODE: Int
        ) {
            if (!checkPermission(applicationContext)) {
                ActivityCompat.requestPermissions(
                    applicationContext,
                    permissionArray,
                    PERMISSION_REQUEST_CODE
                )
            }
        }
    }
}