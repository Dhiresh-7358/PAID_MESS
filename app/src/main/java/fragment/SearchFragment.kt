package fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.paidmess.R
import com.example.paidmess.databinding.FragmentSearchBinding
import dataAndAdapter.CategoryMemberAdapter
import dataAndAdapter.LikesAdapter
import dataAndAdapter.SearchAdapter
import roomDatabase.Food



class SearchFragment : Fragment() {

    class ViewHolder(private val item: View): RecyclerView.ViewHolder(item) {
        val foodName=item.findViewById<TextView>(R.id.item_name)
        val itemImage=item.findViewById<ImageView>(R.id.item_image)
        val price=item.findViewById<TextView>(R.id.price)
    }



    private lateinit var binding:FragmentSearchBinding
    private lateinit var searchRecyclerView:RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding= FragmentSearchBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.totalItems.text = items.toString()

        searchRecyclerView=  binding.searchRecycler
        searchRecyclerView.apply {
            layoutManager= GridLayoutManager(activity, 2)
            adapter= SearchAdapter()

            ( searchRecyclerView.adapter as SearchAdapter).searchItemData= itemListMembers
        }
    }

    companion object {

        fun newInstance() = SearchFragment()

    }
}