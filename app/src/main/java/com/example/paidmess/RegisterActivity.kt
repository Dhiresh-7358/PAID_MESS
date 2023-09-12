package com.example.paidmess

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.paidmess.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var firebaseDataBase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.progressBar.visibility = View.INVISIBLE

        binding.login.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.registerButton.setOnClickListener {
            register()
        }

        binding.backArrow.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun register() {
        auth = FirebaseAuth.getInstance()

        val name = binding.name.text.toString()
        val email = binding.email.text.toString()
        val pass = binding.password.text.toString()
        val comPass = binding.confirmPassword.text.toString()

        if (name.isNotEmpty() && email.isNotEmpty() && pass.isNotEmpty() && comPass.isNotEmpty()) {
            if (pass == comPass) {
                if (pass.length > 7 ) {
                    if(name.length <50){

                        setNameFirebase(name,email)

                        binding.progressBar.visibility = View.VISIBLE
//                        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
//                            if (it.isSuccessful) {
//                                binding.progressBar.visibility = View.INVISIBLE
//                               // startActivity(Intent(this, MainActivity::class.java))
//                            } else {
//                                Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
//                            }
//                        }
                    }else{
                        Toast.makeText(this, "User name have at-most 50 characters ", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Password have at-least 8 characters", Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                Toast.makeText(this, "Password is not matched", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this, "Fields can't empty", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setNameFirebase(name:String,email:String) {

//        val fireStore = Firebase.firestore

//
//        firebaseDataBase=Firebase.database.reference
//
//        if (email != null) {
//            firebaseDataBase.child(email).setValue(name).addOnCompleteListener {
//                if(it.isSuccessful){
//                    Toast.makeText(this,"username added",Toast.LENGTH_SHORT).show()
//                } else{
//                    Toast.makeText(this,"not added",Toast.LENGTH_SHORT).show()
//                }
//            }
//        }
//
//        val username= hashMapOf(
//            "username" to name
//        )
//        if (firebaseUser != null) {
//            fireStore.collection("Users").document("name").set(username).addOnCompleteListener {
//                if (it.isSuccessful){
//                    Toast.makeText(this,"username added",Toast.LENGTH_SHORT).show()
//                }
//                else{
//                    Toast.makeText(this,"username Not",Toast.LENGTH_SHORT).show()
//                }
//            }
//        }

        val db = Firebase.firestore
        val user = hashMapOf(
            "first" to "Ada",
            "last" to "Lovelace",
            "born" to 1815,
        )

// Add a new document with a generated ID
        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Toast.makeText(this,"username added",Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this,"not added",Toast.LENGTH_SHORT).show()
            }
    }
}