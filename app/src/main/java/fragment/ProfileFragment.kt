package fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import com.example.paidmess.*
import com.example.paidmess.databinding.FragmentLikesBinding
import com.example.paidmess.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dataAndAdapter.PrefConstants
import dataAndAdapter.SharedPref


class ProfileFragment : Fragment() {

    private lateinit var binding:FragmentProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentProfileBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getNameFirebase()

        binding.logoutArrow.setOnClickListener {
            auth=FirebaseAuth.getInstance()
            SharedPref.putBoolean(PrefConstants.IS_USER_LOGGED_IN, false)
            startActivity(Intent(activity,LoginActivity::class.java))
        }
        binding.aboutArrow.setOnClickListener {
            startActivity(Intent(activity,MainActivity2::class.java))
        }

        binding.editProfile.setOnClickListener{
            
        }

    }
    private fun getNameFirebase(){
        val db=Firebase.firestore
        val auth=FirebaseAuth.getInstance()

        val firebaseUser=auth.currentUser

        if (firebaseUser != null) {
            db.collection(firebaseUser.uid).get().addOnCompleteListener {
                if (it.isSuccessful){
                    binding.userName.text=it.result.toString()
                }
            }
        }
    }

    companion object {

        fun newInstance() = ProfileFragment()
    }
}