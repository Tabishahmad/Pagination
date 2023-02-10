package com.example.bookapi.common

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.bookapi.R

object BookAppUtil {
    fun ImageView.loadWithGlide(context: Context, imageURL: String) {
        Glide.with(context)
            .load(imageURL)
            .thumbnail(Glide.with(context).load(R.raw.load))
            .into(this)
    }
    fun String.showToast(context:Context){
        Toast.makeText(context,this,Toast.LENGTH_LONG).show()
    }
}