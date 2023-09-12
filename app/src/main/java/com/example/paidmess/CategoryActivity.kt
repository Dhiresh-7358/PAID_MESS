package com.example.paidmess

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.paidmess.databinding.ActivityCategoryBinding
import dataAndAdapter.FoodMemberAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import roomDatabase.Food
import roomDatabase.FoodDatabase
import roomDatabase.FoodItemsModel

class CategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryBinding
    private lateinit var categoryRecycler: RecyclerView
    private lateinit var dishName: String
    private lateinit var categoryName: String
    private lateinit var categoryMutableList: MutableList<FoodItemsModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        categoryMutableList = mutableListOf()

        categoryName = intent.getStringExtra("categoryName").toString()

        binding.categoryTittle.text = categoryName


        when (categoryName.lowercase()) {

            "paneer" -> {
                fetch(10)
            }

            "biryani" -> {
                fetch(20)
            }

        }
    }

    private fun fetch(id: Int) {
        Log.d("size1", "fetch call ho gya")

        val database = FoodDatabase.getDatabase(this)
        database.foodDao().fetFoodCategory(id).observe(this) { it ->
            categoryMutableList.clear()
            categoryMutableList.addAll(it)

            categoryRecycler = binding.categoryRecycler
            categoryRecycler.apply {
                layoutManager = GridLayoutManager(this@CategoryActivity, 2)
                adapter = FoodMemberAdapter({

                    val intent = Intent(this@CategoryActivity, DishActivity::class.java)
                    intent.putExtra("key", it)
                    startActivity(intent)
                }, this@CategoryActivity)

                (categoryRecycler.adapter as FoodMemberAdapter).foodItemData =
                    categoryMutableList
            }
        }

    }
}