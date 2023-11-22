package com.example.parcialgrupo3whale.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.parcialgrupo3whale.R
import com.example.parcialgrupo3whale.database.entities.PetEntity

class DetailFragment : Fragment() {
    private var petEntity: PetEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            petEntity = it.getParcelableOrNull(PET_ENTITY)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)

        val petNameTextView: TextView = view.findViewById(R.id.name_pet_detail)
        val ageTextView: TextView = view.findViewById(R.id.age_detail_pet)
        val locationTextView: TextView = view.findViewById(R.id.city_pet_detail)
        val weightTextView: TextView = view.findViewById(R.id.weight_pet_detail)
        val genderTextView: TextView = view.findViewById(R.id.gender_pet_detail)
        val ownerTextView: TextView = view.findViewById(R.id.name_petOwner_detail)
        val imageView: ImageView = view.findViewById(R.id.image_detail_pet)

        petEntity?.let {
            petNameTextView.text = it.petName
            ageTextView.text = it.petAge
            locationTextView.text = it.location.toString()
            weightTextView.text = it.petWeigh
            genderTextView.text = if (it.gender) "Male" else "Female"
            ownerTextView.text = it.owner

            Glide.with(requireContext())
                .load(it.images)
                .into(imageView)
        }

        return view
    }

    companion object {
        const val PET_ENTITY = "petEntity"

        @JvmStatic
        fun newInstance(petEntity: PetEntity) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(PET_ENTITY, petEntity)
                }
            }
    }
}

private fun Bundle.getParcelableOrNull(petEntity: String): PetEntity? =
    this.getParcelable(petEntity)
