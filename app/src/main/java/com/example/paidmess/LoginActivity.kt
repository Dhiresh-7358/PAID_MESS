package com.example.paidmess

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.paidmess.databinding.ActivityLoginBinding
import com.example.paidmess.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dataAndAdapter.PrefConstants
import dataAndAdapter.SharedPref

lateinit var auth: FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.progressBar.visibility = View.INVISIBLE

        binding.loginButton.setOnClickListener {
            login()
        }

        binding.forgotPass.setOnClickListener {
            startActivity(Intent(this, ForgotPasswordActivity::class.java))
        }
        binding.register.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun login() {
        auth = FirebaseAuth.getInstance()

        val email = binding.email.text.toString()
        val pass = binding.password.text.toString()


        if (email.isNotEmpty() && pass.isNotEmpty()) {
            if (pass.length >7) {
                binding.progressBar.visibility = View.VISIBLE
                auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        binding.progressBar.visibility = View.INVISIBLE
                        SharedPref.putBoolean(PrefConstants.IS_USER_LOGGED_IN,true)
                        startActivity(Intent(this, MainActivity::class.java))
                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Password have at-least 8 characters", Toast.LENGTH_SHORT)
                    .show()
            }
        } else {
            Toast.makeText(this, "Fields can't empty", Toast.LENGTH_SHORT).show()
        }
    }
}