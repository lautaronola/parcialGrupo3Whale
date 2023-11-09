package com.example.parcialgrupo3whale.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.appcompat.app.AppCompatActivity
import com.example.parcialgrupo3whale.R
import com.example.parcialgrupo3whale.activities.MainActivity
import com.example.parcialgrupo3whale.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var button: Button
    private lateinit var imageView: ImageView

    companion object{
        val IMAGE_REQUEST_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        val argumentValue = arguments?.getString("userName")
        val textView: TextView = view.findViewById(R.id.nameProfile)

        button = view.findViewById(R.id.upload_photo)
        imageView = view.findViewById(R.id.profile_image)

        button.setOnClickListener {
            pickImageGallery()
        }

        textView.text = argumentValue

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val navController = binding.root.findNavController()

        binding.arrowBackImg.setOnClickListener {

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
    private fun pickImageGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == AppCompatActivity.RESULT_OK) {
            imageView.setImageURI(data?.data)
        }
    }
}