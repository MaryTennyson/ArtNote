package com.ebraratabay.artnote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ebraratabay.artnote.databinding.ActivityArtBinding

class ArtActivity : AppCompatActivity() {
    private lateinit var binding: ActivityArtBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityArtBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    fun onSaveClicked(view: View){


    }

    fun onSelectImage(view:View){

    }
}