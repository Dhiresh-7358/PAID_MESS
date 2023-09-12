package com.example.paidmess

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.paidmess.databinding.ActivityForgotPasswordBinding
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var binding:ActivityForgotPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.progressBar.visibility=View.INVISIBLE

        binding.backArrow.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        }

        binding.resetPassButton.setOnClickListener {
            resetPass()
        }
    }

    private fun resetPass() {
        auth=FirebaseAuth.getInstance()

        val email=binding.email.text.toString()

        if(email.isNotEmpty()){
            binding.progressBar.visibility=View.VISIBLE
            auth.sendPasswordResetEmail(email).addOnCompleteListener {
                if(it.isSuccessful){
                    binding.progressBar.visibility=View.INVISIBLE
                    startActivity(Intent(this,MainActivity::class.java))
                }else{
                    Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }else{
            Toast.makeText(this, "Email can't empty", Toast.LENGTH_SHORT).show()
        }
    }
}