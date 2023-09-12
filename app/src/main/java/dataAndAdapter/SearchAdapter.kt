package dataAndAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.paidmess.R
import fragment.SearchFragment
import roomDatabase.FoodItemsModel

class SearchAdapter: RecyclerView.Adapter<SearchFragment.ViewHolder>() {

    var searchItemData= mutableListOf<FoodItemsModel>()
        set(value){
            field=value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchFragment.ViewHolder {
        val itemLayout= LayoutInflater.from(parent.context).inflate(
            R.layout.food_type,parent,false)

        return SearchFragment.ViewHolder(itemLayout)
    }

    override fun onBindViewHolder(holder: SearchFragment.ViewHolder, position: Int) {
        with(holder){
            itemImage.setImageResource(searchItemData[position].foodImage)
            foodName.text=searchItemData[position].foodName
            price.text=searchItemData[position].foodPrice.toString()
        }

    }

    override fun getItemCount(): Int {
      return searchItemData.size
    }
}