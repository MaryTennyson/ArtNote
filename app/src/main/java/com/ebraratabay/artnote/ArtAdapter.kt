package com.ebraratabay.artnote

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ebraratabay.artnote.databinding.RecyclerrowBinding

class ArtAdapter(val artList: ArrayList<Art>) : RecyclerView.Adapter<ArtAdapter.ArtHolder> (){
    class ArtHolder(val binding: RecyclerrowBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtHolder {
        val binding= RecyclerrowBinding.inflate(LayoutInflater.from(parent.context), parent,false)
    return ArtHolder(binding)
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ArtHolder, position: Int) {
        TODO("Not yet implemented")
    }
}