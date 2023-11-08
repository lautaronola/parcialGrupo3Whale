package com.example.parcialgrupo3whale.holders

import android.view.View
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.parcialgrupo3whale.R

class PetHolder (view: View) : RecyclerView.ViewHolder(view){

    private var view : View

    init {
        this.view = view
    }

    fun getCardLayout() : CardView {
        return view.findViewById(R.id.card_view_item)
    }
}
