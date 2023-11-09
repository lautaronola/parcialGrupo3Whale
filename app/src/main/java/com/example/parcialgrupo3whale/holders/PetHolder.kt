package com.example.parcialgrupo3whale.holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.parcialgrupo3whale.R
import com.example.parcialgrupo3whale.database.entities.PetEntity

class PetHolder (view: View) : RecyclerView.ViewHolder(view){

    private var view : View

    init {
        this.view = view
    }

    private val petNameTextView: TextView = view.findViewById(R.id.name_dog)
    private val breedTextView: TextView = view.findViewById(R.id.breeds_dog)
    private val subBreedTextView: TextView = view.findViewById(R.id.sub_breeds_dog)
    private val petImageView: ImageView = view.findViewById(R.id.imageViewBackground)

    fun bind(pet: PetEntity) {
        petNameTextView.text = pet.petName
        breedTextView.text = pet.breed
        subBreedTextView.text = pet.subBreed

        Glide.with(view.context)
            .load(pet.images)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .into(petImageView)

       /* Glide.with(view.context)
            .load(pet.images)
            .into(petImageView)*/
    }

    fun getCardLayout() : CardView {
        return view.findViewById(R.id.card_view_item)
    }


}
