package fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.paidmess.R
import com.example.paidmess.SearchActivity2
import com.example.paidmess.databinding.FragmentCartBinding
import com.example.paidmess.databinding.FragmentHomeBinding
import dataAndAdapter.CartItemsAdapter
import dataAndAdapter.CartItemsModel
import kotlinx.coroutines.*
import roomDatabase.Cart
import roomDatabase.FoodDatabase
import kotlin.coroutines.coroutineContext

class CartFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private lateinit var binding: FragmentCartBinding
    private lateinit var itemCart: MutableList<Cart>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemCart = mutableListOf()

        fetch()

        binding.addMoreItems.setOnClickListener {
            startActivity(Intent(activity,SearchActivity2::class.java))
        }

        binding.arrowAddItems.setOnClickListener {
            startActivity(Intent(activity,SearchActivity2::class.java))
        }

    }

    private fun fetch() {
        val database = FoodDatabase.getDatabase(requireContext())
        database.cartDao().fetchAllItems().observe(viewLifecycleOwner) {
            itemCart.clear()
            itemCart.addAll(it)

            if (itemCart.isEmpty()) {
                binding.emptyCartImage.setImageResource(R.drawable.empty_cart)
                binding.emptyCartImage.layoutParams.height = 420
                binding.emptyCartImage.layoutParams.width = 420

                binding.emptyCartText.text = "Your cart is empty"
                binding.emptyCartText.textSize = "24".toFloat()

            }

            val adapterCart = CartItemsAdapter(itemCart, requireContext())
            val categoryRecyclerView: RecyclerView = binding.cartRecycler
            categoryRecyclerView.layoutManager = LinearLayoutManager(
                activity,
                RecyclerView.VERTICAL, false
            )
            categoryRecyclerView.adapter = adapterCart


            var total = 0
            var items = 0
            for (i in itemCart) {
                total += ((i.foodPrice) * (i.quantity))
                items += (i.quantity)
            }
            binding.noItems.text = items.toString()
            binding.totalAmount.text = total.toString()

        }


    }

    companion object {

        fun newInstance() = CartFragment()
    }
}

