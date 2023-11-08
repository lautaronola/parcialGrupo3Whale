package com.example.parcialgrupo3whale.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import com.example.parcialgrupo3whale.R
import com.example.parcialgrupo3whale.activities.MainActivity
import com.example.parcialgrupo3whale.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        val argumentValue = arguments?.getString("userName")
        val textView: TextView = view.findViewById(R.id.nameProfile)

        textView.text = argumentValue

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val navController = binding.root.findNavController()

        binding.arrowBackImg.setOnClickListener{

            if (navController != null) navController.popBackStack(R.id.nav_graph_xml, false)

            if (activity is MainActivity) {
                (activity as MainActivity).setBottomNavViewVisibility(View.VISIBLE)
                (activity as MainActivity).supportActionBar?.show()
            }

            navController.popBackStack()
            true
        }
        super.onViewCreated(view, savedInstanceState)
    }
}