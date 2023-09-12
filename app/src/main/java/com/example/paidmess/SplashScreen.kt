package com.example.paidmess

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dataAndAdapter.PrefConstants
import dataAndAdapter.SharedPref

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val isUserLoggedIn = SharedPref.getBoolean(PrefConstants.IS_USER_LOGGED_IN)

        if (isUserLoggedIn) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}