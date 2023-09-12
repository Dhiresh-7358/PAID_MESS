package dataAndAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.paidmess.R
import roomDatabase.FoodItemsModel
import roomDatabase.Like

class LikesAdapter: RecyclerView.Adapter<LikesAdapter.ViewHolder>() {

    var likesItemData= mutableListOf<FoodItemsModel>()
        set(value){
            field=value
            notifyDataSetChanged()
        }


    class ViewHolder(private val item: View): RecyclerView.ViewHolder(item) {
        val foodName=item.findViewById<TextView>(R.id.item_name)
        val itemImage=item.findViewById<ImageView>(R.id.item_image)
        val price=item.findViewById<TextView>(R.id.price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        val itemLayout=LayoutInflater.from(parent.context).inflate(
            R.layout.food_type,parent,false)

        return ViewHolder(itemLayout)
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        with(holder){
            itemImage.setImageResource(likesItemData[position].foodImage)
            foodName.text=likesItemData[position].foodName
            price.text=likesItemData[position].foodPrice.toString()

        }
    }

    override fun getItemCount(): Int {
       return likesItemData.size
    }
}