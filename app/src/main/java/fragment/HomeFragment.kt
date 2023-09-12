package fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.paidmess.*
import com.example.paidmess.databinding.FragmentHomeBinding
import dataAndAdapter.CategoryMemberAdapter
import dataAndAdapter.CategoryMemberModel
import roomDatabase.FoodItemsModel
import dataAndAdapter.FoodMemberAdapter
import kotlinx.coroutines.*
import roomDatabase.FoodDatabase

val itemListMembers = mutableListOf<FoodItemsModel>(
    FoodItemsModel(
        101,
        "Matar \nPaneer",
        60,
        false,
        false,
        1,
        "An Indian dish made from chunks of paneer/ chhena marinated in spices and grilled in a tandoor.",
        true,
        R.drawable.matar_paneer
    ),
    FoodItemsModel(
        102,
        "Shahi\nPaneer",
        70,
        false,
        false,
        1,
        "An Indian dish made from chunks of paneer/ chhena marinated in spices and grilled in a tandoor.",
        true,
        R.drawable.imgbin_shahi_paneer_palak_paneer_paneer_tikka_masala_paneer_makhani_karahi_png
    ),
    FoodItemsModel(
        103,
        "Butter \nPaneer",
        80,
        false,
        false,
        1,
        "An Indian dish made from chunks of paneer/ chhena marinated in spices and grilled in a tandoor.",
        true,
        R.drawable.paneer
    ),
    FoodItemsModel(
        104,
        "Kadahai \nPaneer",
        90,
        false,
        false,
        1,
        "An Indian dish made from chunks of paneer/ chhena marinated in spices and grilled in a tandoor.",
        true,
        R.drawable.paneer
    ),
    FoodItemsModel(
        201,
        "Chicken \nBiryani",
        100,
        false,
        false,
        1,
        "An Indian dish made from chunks of paneer/ chhena marinated in spices and grilled in a tandoor.",
        false,
        R.drawable.chicken_biryani
    )
)

class HomeFragment : Fragment() {

    private lateinit var popularRecyclerView: RecyclerView
    private lateinit var binding: FragmentHomeBinding
    private lateinit var categoryName: String
    private lateinit var mutableList: Array<FoodItemsModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.searchBar.setOnClickListener {
            startActivity(Intent(activity, SearchActivity2::class.java))
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mutableList = arrayOf()


        val mutableListCategory: MutableList<CategoryMemberModel> = mutableListOf(
            CategoryMemberModel(R.drawable.balanced_diet, "Chicken"),
            CategoryMemberModel(R.drawable.egg, "Egg"),
            CategoryMemberModel(R.drawable.rice, "Biryani"),
            CategoryMemberModel(R.drawable.ramen, "Noodle"),
            CategoryMemberModel(R.drawable.paneer, "Paneer"),
            CategoryMemberModel(R.drawable.balanced_diet, "Chicken"),
            CategoryMemberModel(R.drawable.egg, "Egg"),
            CategoryMemberModel(R.drawable.rice, "Biryani"),
            CategoryMemberModel(R.drawable.ramen, "Noodle")

        )

        fetchPopularData()

        CoroutineScope(Dispatchers.IO).launch {
            insertAllFood()

            withContext(Dispatchers.Main) {

                val categoryRecyclerView: RecyclerView = binding.categoryRecycler
                categoryRecyclerView.apply {
                    layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
                    adapter = CategoryMemberAdapter {
                        categoryName = it.categoryName
                        val intent = Intent(activity, CategoryActivity::class.java)
                        intent.putExtra("categoryName", categoryName)
                        startActivity(intent)
                    }
                }
                (categoryRecyclerView.adapter as CategoryMemberAdapter).categoryItemData =
                    mutableListCategory
            }
        }

    }

    private fun notifyDataSetChange() {
        popularRecyclerView = binding.popularRecycler
        popularRecyclerView.apply {
            layoutManager = GridLayoutManager(activity, 2)
            adapter = FoodMemberAdapter(
                { foodItemsModel -> adapterClick(foodItemsModel) },
                requireContext()
            )

        }
        (popularRecyclerView.adapter as FoodMemberAdapter).foodItemData = itemListMembers
    }

    private fun fetchPopularData() {
        val database = FoodDatabase.getDatabase(requireContext())
        Log.d("size1", "fun call ho gya")
        database.foodDao().getFoodItems().observe(viewLifecycleOwner) {
            itemListMembers.clear()
            itemListMembers.addAll(it)
            notifyDataSetChange()
        }
    }

    private suspend fun insertAllFood() {
        val database = FoodDatabase.getDatabase(requireContext())
        database.foodDao().insert(itemListMembers)
    }

    private fun adapterClick(foodItemsModel: FoodItemsModel) {
        val intent = Intent(activity, DishActivity::class.java)
        intent.putExtra("key", foodItemsModel)
        startActivity(intent)
    }

    companion object {

        fun newInstance() = HomeFragment()

    }
}