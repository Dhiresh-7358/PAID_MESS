package dataAndAdapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.compose.ui.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import roomDatabase.Food
import com.example.paidmess.R

class CategoryMemberAdapter(private val listener:(CategoryMemberModel)->Unit) : RecyclerView.Adapter<CategoryMemberAdapter.ViewHolder>() {

    var categoryItemData= mutableListOf<CategoryMemberModel>()
        set(value){
            field=value
            notifyDataSetChanged()
        }

   inner class ViewHolder(item:View):RecyclerView.ViewHolder(item) {

            val image:ImageView=item.findViewById(R.id.category_image)
            val name:TextView=item.findViewById(R.id.category_name)
            val card:CardView=item.findViewById(R.id.card_item)


        init {
            itemView.setOnClickListener{
//              card.setCardBackgroundColor(Color(1,0,0,1,)
                listener.invoke(categoryItemData[adapterPosition])
            }
        }
    }

    var lastPosition=0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
            .inflate(R.layout.category_items_member, parent, false)
        return ViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            if (position > lastPosition) {
               // val sideIn: Animation = AnimationUtils.loadAnimation(itemView.context,R.anim.fall_down)
             //   itemView.startAnimation(sideIn)
                lastPosition=position
            }

            image.setImageResource(categoryItemData[position].categoryImage)
            name.text = categoryItemData[position].categoryName

        }
    }

    override fun getItemCount(): Int {
        return categoryItemData.size
    }
}