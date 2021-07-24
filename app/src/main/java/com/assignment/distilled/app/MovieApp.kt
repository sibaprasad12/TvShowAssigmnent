package com.assignment.distilled.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


/**
 * Created by Sibaprasad Mohanty on 21/07/21.
 * Spm Limited
 * sp.dobest@gmail.com
 */
@HiltAndroidApp
class MovieApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}