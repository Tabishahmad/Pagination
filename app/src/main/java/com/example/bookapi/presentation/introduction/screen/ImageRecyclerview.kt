package com.example.bookapi.presentation.introduction.screen

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ImageRecyclerview : RecyclerView {

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private fun init() {
        layoutManager = GridLayoutManager(context, 3)
        adapter = ImageListAdapter()
    }

    private fun getMAdapter(): ImageListAdapter {
        return adapter as ImageListAdapter
    }

    fun setData(list: List<Any>) {
        getMAdapter().setList(list)
    }


    fun setItemClickListener(listener: ImageListAdapter.CardClickListener) {
        getMAdapter().setClickListener(listener)
    }

}