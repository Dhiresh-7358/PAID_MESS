package dataAndAdapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.paidmess.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import roomDatabase.FoodDatabase
import roomDatabase.FoodItemsModel
import roomDatabase.Like

class FoodMemberAdapter(
    private val listener: (FoodItemsModel) -> Unit,
    private val context: Context
) : RecyclerView.Adapter<FoodMemberAdapter.FoodItemViewHolder>() {

    val database = FoodDatabase.getDatabase(context)

    var foodItemData = mutableListOf<FoodItemsModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class FoodItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemName: TextView = view.findViewById(R.id.item_name)
        val itemPrice: TextView = view.findViewById(R.id.price)
        val itemImage: ImageView = view.findViewById(R.id.item_image)
        val itemLiked: ImageView = view.findViewById(R.id.like)

        init {
            itemView.setOnClickListener {
                listener.invoke(foodItemData[adapterPosition])
            }
            itemName.setOnClickListener {
                listener.invoke(foodItemData[adapterPosition])
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodItemViewHolder {
        val itemLayout = LayoutInflater.from(parent.context).inflate(
            R.layout.food_type, parent, false
        )

        return FoodItemViewHolder(itemLayout)
    }

    override fun onBindViewHolder(holder: FoodItemViewHolder, position: Int) {
        with(holder) {

            var liked = foodItemData[position].isLiked

            if (liked) {
                itemLiked.setImageResource(R.drawable.heart_purple)
            }

            val zoomIn = AnimationUtils.loadAnimation(context, R.anim.zoom_in)

            itemLiked.setOnClickListener {
                if (liked) {
                    itemLiked.setImageResource(R.drawable.heart)

                    CoroutineScope(Dispatchers.IO).launch {

                        deleteFromLike(false, foodItemData[position])
                    }
                } else {
                    itemLiked.setImageResource(R.drawable.heart_purple)
                    CoroutineScope(Dispatchers.IO).launch {
                        insertToLike(true, foodItemData[position])
                    }
                }
                itemLiked.startAnimation(zoomIn)
                liked = !liked
            }
            itemName.text = foodItemData[position].foodName
            itemPrice.text = foodItemData[position].foodPrice.toString()
            itemImage.setImageResource(foodItemData[position].foodImage)

        }

    }

    private suspend fun deleteFromLike(like: Boolean, foodItemData: FoodItemsModel) {
        database.likeDao().deleteLikeItem(foodItemData.id)
        database.foodDao().updateLike(like, foodItemData.id)
    }

    private suspend fun insertToLike(like: Boolean, foodItemData: FoodItemsModel) {
        database.likeDao().insertLikedItems(
            Like(
                foodItemData.id,
                foodItemData.foodName,
                foodItemData.foodPrice,
                like,
                foodItemData.inCart,
                foodItemData.quantity,
                foodItemData.about,
                foodItemData.isVeg,
                foodItemData.foodImage
            )
        )
        database.foodDao().updateLike(like, foodItemData.id)
    }

    override fun getItemCount(): Int {
        return foodItemData.size
    }


}