package com.example.paidmess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.paidmess.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.BaseTransientBottomBar
import fragment.*



class MainActivity : AppCompatActivity() {


    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        binding.world.setOnClickListener {
//            startActivity(Intent(this,LoginActivity::class.java))
//        }

        binding.bottomBar.setOnItemSelectedListener {

            when (it.itemId) {
                R.id.nav_home -> {
                    layoutFragment(HomeFragment.newInstance())
                }
                R.id.nav_cart -> {
                    layoutFragment(CartFragment.newInstance())
                }
                R.id.nav_like -> {
                    layoutFragment(LikesFragment.newInstance())
                }
                R.id.nav_profile -> {
                    layoutFragment(ProfileFragment.newInstance())
                }
            }
            true
        }
        binding.bottomBar.selectedItemId=R.id.nav_home
    }

    private fun layoutFragment(newInstance:Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,newInstance)
        transaction.commit()
    }
}