package com.example.paidmess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.google.android.material.snackbar.BaseTransientBottomBar.AnimationMode

class MainActivity2 : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        val zoomIn =AnimationUtils.loadAnimation(this,R.anim.zoom_in)
        var liked=false

        val like=findViewById<ImageView>(R.id.likedd)
        like.setOnClickListener {

            if (liked){
                like.setImageResource(R.drawable.heart_purple)
            }
            else{
                like.setImageResource(R.drawable.heart)
            }
            like.startAnimation(zoomIn)
            liked=!liked

        }
    }
}