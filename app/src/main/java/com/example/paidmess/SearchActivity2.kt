package com.example.paidmess

import android.content.Intent
import android.content.SearchRecentSuggestionsProvider
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.paidmess.databinding.ActivitySearch2Binding
import dataAndAdapter.FoodMemberAdapter
import fragment.itemListMembers
import roomDatabase.FoodItemsModel

class SearchActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivitySearch2Binding
    private lateinit var popularRecyclerView: RecyclerView
    private lateinit var newList: MutableList<FoodItemsModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearch2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        newList = mutableListOf()
        newList.addAll(itemListMembers)

        popularRecyclerView = binding.searchRecycler
        popularRecyclerView.apply {
            layoutManager = GridLayoutManager(this@SearchActivity2, 2)
            adapter = FoodMemberAdapter(
                { foodItemsModel -> adapterClick(foodItemsModel) },
                this@SearchActivity2
            )

        }
        (popularRecyclerView.adapter as FoodMemberAdapter).foodItemData = newList

        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(p0: String?): Boolean {

                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {

                newList.clear()
                filterList(p0)
                return true
            }

        })
    }

    private fun filterList(p0: String?) {
        for (i in itemListMembers) {
            if (p0 != null) {
                if (i.foodName.lowercase().contains(p0.lowercase())) {
                    newList.add(i)
                }
            }
        }

        popularRecyclerView.adapter?.notifyDataSetChanged()

        if (newList.isEmpty()) {
            Toast.makeText(this, "Food item not found!!!", Toast.LENGTH_SHORT).show()
        } else {

        }

    }

    private fun adapterClick(foodItemsModel: FoodItemsModel) {
        val intent = Intent(this, DishActivity::class.java)
        intent.putExtra("key", foodItemsModel)
        startActivity(intent)
    }


}