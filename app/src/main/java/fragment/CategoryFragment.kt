package fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.paidmess.R
import com.example.paidmess.databinding.FragmentCategoryBinding
import com.example.paidmess.databinding.FragmentHomeBinding
import dataAndAdapter.FoodMemberAdapter

class CategoryFragment : Fragment() {

    private lateinit var binding:FragmentCategoryBinding
    private lateinit var categoryRecycler:RecyclerView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentCategoryBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        categoryRecycler=binding.categoryRecycler
//        categoryRecycler.apply {
//            layoutManager=GridLayoutManager(activity,2)
//            adapter=FoodMemberAdapter()
//
//            ( categoryRecycler.adapter as FoodMemberAdapter).foodItemData= itemListMembers
//        }


    }

    companion object {

        fun newInstance() = CategoryFragment()
    }
}