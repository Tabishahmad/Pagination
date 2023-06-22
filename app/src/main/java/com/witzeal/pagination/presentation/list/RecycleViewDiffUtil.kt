package com.witzeal.pagination.presentation.list

import androidx.recyclerview.widget.DiffUtil
import com.witzeal.pagination.domain.model.User

class RecycleViewDiffUtil(
    private val oldList: List<User>,
    private val newList: List<User>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].user_name == newList[newItemPosition].user_name &&
                oldList[oldItemPosition].rank == newList[newItemPosition].rank
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        // Compare the contents of the items
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}
