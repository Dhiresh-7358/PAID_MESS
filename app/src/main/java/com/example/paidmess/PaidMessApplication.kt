package com.example.paidmess

import android.app.Application
import dataAndAdapter.SharedPref

class PaidMessApplication:Application() {

    override fun onCreate() {
        super.onCreate()

        SharedPref.init(this)
    }
}