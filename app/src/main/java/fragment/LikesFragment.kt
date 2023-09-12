package fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.paidmess.DishActivity
import com.example.paidmess.R
import com.example.paidmess.databinding.FragmentLikesBinding
import dataAndAdapter.FoodMemberAdapter
import dataAndAdapter.LikeModel
import dataAndAdapter.LikesAdapter
import roomDatabase.FoodDatabase
import roomDatabase.FoodItemsModel
import roomDatabase.Like

class LikesFragment : Fragment() {

    private lateinit var binding: FragmentLikesBinding
    private lateinit var likeRecycler: RecyclerView
    private lateinit var like: TextView
    private lateinit var likeMutableList: MutableList<FoodItemsModel>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLikesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        likeMutableList = mutableListOf()
        fetchLikeItem()

    }

    private fun fetchLikeItem() {
        val database = FoodDatabase.getDatabase(requireContext())
        database.likeDao().getLikeItems().observe(viewLifecycleOwner) {
            likeMutableList.clear()
            likeMutableList.addAll(it)

            likeRecycler = binding.likeRecycler
            likeRecycler.apply {
                layoutManager = GridLayoutManager(activity, 2)
                adapter = FoodMemberAdapter ({

                    val intent= Intent(activity, DishActivity::class.java)
                    intent.putExtra("key",it)
                    startActivity(intent)
                },requireContext())

                (likeRecycler.adapter as FoodMemberAdapter).foodItemData =
                    likeMutableList
            }
        }
    }

    companion object {

        fun newInstance() = LikesFragment()
    }
}