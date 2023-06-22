package com.witzeal.pagination.presentation.list

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.witzeal.pagination.domain.model.User

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

    fun setData(list: List<User>) {
        getMAdapter().updateList(list)
    }
    fun updateList(list: List<User>,callback: () -> Unit) {
        getMAdapter().addNewItems(list){
            callback()
        }
    }

    fun setItemClickListener(listener: ImageListAdapter.ItemClickListener) {
        getMAdapter().setClickListener(listener)
    }

}


