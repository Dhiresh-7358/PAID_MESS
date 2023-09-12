package dataAndAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.paidmess.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import roomDatabase.Cart
import roomDatabase.FoodDatabase


class CartItemsAdapter(private val itemsCart: MutableList<Cart>, private val context: Context) :
    RecyclerView.Adapter<CartItemsAdapter.CartViewHolder>() {

    val database = FoodDatabase.getDatabase(context)

    class CartViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.veg)
        val cartItemName: TextView = view.findViewById(R.id.item_name)
        val itemPrice: TextView = view.findViewById(R.id.item_price)
        val itemQuantity: TextView = view.findViewById(R.id.item_count)
        val totalItemPrice: TextView = view.findViewById(R.id.total_items_price)
        val addButton: ImageView = view.findViewById(R.id.add)
        val minusButton: ConstraintLayout = view.findViewById(R.id.minus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val inflater = LayoutInflater.from(parent.context)
            .inflate(R.layout.cart_items, parent, false)
        return CartViewHolder(inflater)
    }


    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        with(holder) {

            var quantity = itemsCart[position].quantity

            if (itemsCart[position].isVeg) {
                image.setImageResource(R.drawable.vegan)

            } else {
                image.setImageResource(R.drawable.no_meat)
            }

            itemQuantity.text = quantity.toString()

            addButton.setOnClickListener {
                quantity++
                CoroutineScope(Dispatchers.IO).launch {
                    addQuantity(quantity, itemsCart[position])
                }

                itemQuantity.text = (quantity).toString()
            }
            minusButton.setOnClickListener {
                quantity--
                CoroutineScope(Dispatchers.IO).launch {
                    addQuantity(quantity, itemsCart[position])
                }

                itemQuantity.text = (quantity).toString()
            }

            if (quantity == 0) {
                CoroutineScope(Dispatchers.IO).launch {
                    removeItem(itemsCart[position].id)
                }
            }

            itemPrice.text = itemsCart[position].foodPrice.toString()
            cartItemName.text = itemsCart[position].foodName

            val price = ((itemsCart[position].foodPrice) * (itemsCart[position].quantity))
            totalItemPrice.text = price.toString()

        }
    }

    private suspend fun removeItem(id: Long) {
        database.cartDao().deleteItem(id)
        database.foodDao().updateCart(false,id)
    }

    private suspend fun addQuantity(quantity: Int, itemCart: Cart) {
        database.cartDao().updateQuantity(itemCart.id, quantity)
    }

    override fun getItemCount(): Int {
        return itemsCart.size
    }
}

