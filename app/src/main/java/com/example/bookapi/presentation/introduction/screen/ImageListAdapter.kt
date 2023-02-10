package com.example.bookapi.presentation.introduction.screen

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.bookapi.common.gone
import com.example.bookapi.common.loadWithGlide
import com.example.bookapi.common.visible
import com.example.bookapi.databinding.ImageRowBinding
import com.example.bookapi.domain.model.Book

class ImageListAdapter : RecyclerView.Adapter<ImageListAdapter.ImageHolder>() {

    private var layoutInflater: LayoutInflater? = null
    private var imageClickListener: ImageClickListener? = null

    class ImageHolder(val b: ImageRowBinding) : RecyclerView.ViewHolder(b.root) {

        fun setImage(thumbnailURL:String) {
            b.iv.loadWithGlide(b.iv.context, thumbnailURL)
        }
        fun handleFavorite(isFav : Boolean){
            if (isFav){
                b.fav.visible()
            }else{
                b.fav.gone()
            }
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
        val book = differ.currentList[position]

        holder.setImage(book.thumbnailUrl)

        holder.handleFavorite(book.isFav)
        holder.b.card.setOnClickListener {
            val book = differ.currentList[position]
            println("clickDebug 1 " + book.isFav)
            book.isFav = !book.isFav
            println("clickDebug 2 " + book.isFav)
            imageClickListener?.onImageClick(it, book, position)
            notifyDataSetChanged()

        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


    fun setClickListener(listener: ImageClickListener) {
        this.imageClickListener = listener
    }

    interface ImageClickListener {
        fun onImageClick(view: View, any: Any, index: Int)
    }

    private val differCallback = object : DiffUtil.ItemCallback<Book>() {
        override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
            // check for contents
            return oldItem.equals(newItem)
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem.bookHashId == newItem.bookHashId &&
                    oldItem.bookTitle == newItem.bookTitle &&
                    oldItem.thumbnailUrl == newItem.thumbnailUrl
        }
    }
    val differ = AsyncListDiffer(this,differCallback)
}