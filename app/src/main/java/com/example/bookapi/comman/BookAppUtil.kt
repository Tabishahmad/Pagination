package com.example.bookapi.comman

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.bookapi.R

object BookAppUtil {
    fun setImage(ctx: Context, str: String, iv: ImageView) {
        Glide.with(ctx)
            .load(str)
            .thumbnail(Glide.with(ctx).load(R.raw.load))
            .into(iv)
    }
}