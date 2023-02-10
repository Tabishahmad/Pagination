package com.example.bookapi.presentation.introduction.screen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bookapi.common.BookAppUtil.loadWithGlide
import com.example.bookapi.databinding.ImageRowBinding
import com.example.bookapi.domain.model.Book

class ImageListAdapter : RecyclerView.Adapter<ImageListAdapter.ImageHolder>() {

    var layoutInflater: LayoutInflater? = null

    val list = ArrayList<Any>()
    var listener: CardClickListener? = null

    class ImageHolder(val b: ImageRowBinding) : RecyclerView.ViewHolder(b.root) {

        fun setImage(any: Any) {
            b.iv.loadWithGlide(b.iv.context,(any as Book).thumbnailUrl)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.context)
        }
        val b = ImageRowBinding.inflate(layoutInflater!!, parent, false)
        return ImageHolder(b)
    }

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        holder.setImage(list[position])
        holder.b.card.setOnClickListener {
            listener?.onCardClick(it, list[position], position)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setList(list: List<Any>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    fun setClickListener(listener: CardClickListener) {
        this.listener = listener
    }

    interface CardClickListener {
        fun onCardClick(view: View, any: Any, index: Int)
    }

}