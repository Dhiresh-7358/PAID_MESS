package com.example.paidmess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.example.paidmess.databinding.ActivityMain2Binding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import roomDatabase.Cart
import roomDatabase.FoodDatabase
import roomDatabase.FoodItemsModel
import roomDatabase.Like

class DishActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding
    private var id: Long = 0
    private lateinit var database: FoodDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        database = FoodDatabase.getDatabase(this)

        val zoomIn = AnimationUtils.loadAnimation(this, R.anim.zoom_in)

        val likedFoodItem: FoodItemsModel = intent.getSerializableExtra("key") as FoodItemsModel

        val name = likedFoodItem.foodName
        val price = likedFoodItem.foodPrice.toString()

        binding.dishName.text = name
        binding.totalAmount.text = price
        binding.dishImage.setImageResource(likedFoodItem.foodImage)

        var like = binding.itemLike

        var liked = likedFoodItem.isLiked
        val inCart = likedFoodItem.inCart

        if (inCart) {

            binding.AddToCartButton.text = "Already in cart"
        } else {
            binding.AddToCartButton.text = "Add to cart"
        }

        if (liked) {
            like.setImageResource(R.drawable.heart_purple)
        }

        binding.AddToCartButton.setOnClickListener {
            if (inCart) {
                Toast.makeText(this,"Item already in cart",Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this,"Item is added in cart",Toast.LENGTH_SHORT).show()
                CoroutineScope(Dispatchers.IO).launch {
                    insertTOCart(true, likedFoodItem)
                }
            }

        }

        like.setOnClickListener {

            if (liked) {
                like.setImageResource(R.drawable.heart)
                CoroutineScope(Dispatchers.IO).launch {
                    deleteFromLike(false, likedFoodItem)
                }
            } else {
                like.setImageResource(R.drawable.heart_purple)
                CoroutineScope(Dispatchers.IO).launch {
                    insertToLike(true, likedFoodItem)
                }
            }
            like.startAnimation(zoomIn)
            liked = !liked

        }
    }

    private suspend fun insertToLike(like: Boolean, likedFoodItem: FoodItemsModel) {
        database.likeDao().insertLikedItems(
            Like(
                likedFoodItem.id,
                likedFoodItem.foodName,
                likedFoodItem.foodPrice,
                like,
                likedFoodItem.inCart,
                likedFoodItem.quantity,
                likedFoodItem.about,
                likedFoodItem.isVeg,
                likedFoodItem.foodImage
            )
        )
        database.foodDao().updateLike(like, likedFoodItem.id)
    }

    private suspend fun deleteFromLike(like: Boolean, foodItemData: FoodItemsModel) {
        database.likeDao().deleteLikeItem(foodItemData.id)
        database.foodDao().updateLike(like, foodItemData.id)
    }

    private suspend fun insertTOCart(inCart: Boolean, likedFoodItems: FoodItemsModel) {
        database.cartDao().insetItem(
            Cart(
                likedFoodItems.id,
                likedFoodItems.foodName,
                likedFoodItems.foodPrice,
                likedFoodItems.quantity,
                likedFoodItems.isVeg
            )
        )
        database.foodDao().updateCart(inCart, likedFoodItems.id)

    }
}