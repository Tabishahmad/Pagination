package com.witzeal.pagination.presentation.list

import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.witzeal.pagination.common.OrdinalSuperscriptFormatter
import com.witzeal.pagination.common.loadImageWithGlide
import com.witzeal.pagination.databinding.ImageRowBinding
import com.witzeal.pagination.domain.model.User
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList


class ImageListAdapter : RecyclerView.Adapter<ImageListAdapter.ImageHolder>() {

    private var layoutInflater: LayoutInflater? = null
    private var imageClickListener: ItemClickListener? = null
    private var items: ArrayList<User> = emptyUserArrayList()

    inner   class ImageHolder(val b: ImageRowBinding) : RecyclerView.ViewHolder(b.root) {
       fun setUserData(user: User) {
           b.profileImage.loadImageWithGlide(b.profileImage.context, user.profile_pic_URL)
           //
           b.textName.setText(user.user_name)
           //hard code
           b.textName.setText("John Wick")

           when (user.rank) {
               1 -> b.textRank.setText("${user.rank}st")
               2 -> b.textRank.setText("${user.rank}nd")
               3 -> b.textRank.setText("${user.rank}rd")
               else -> b.textRank.setText("${user.rank}th")
           }
           val formatter = OrdinalSuperscriptFormatter(SpannableStringBuilder())
           formatter.format(b.textRank);


           val formattedNumber = NumberFormat.getNumberInstance(Locale.getDefault()).format(user.user_point)
            b.textPoint.setText(""+formattedNumber+" pts")
            b.textPriceMoney.setText("₹"+user.price_money)
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
        val user = items.get(position)
        holder.setUserData(user)
//        holder.b.card.setOnClickListener {
//            imageClickListener?.onItemClick(it, book, position)
//        }
    }

    override fun getItemCount(): Int {
        return items.size
    }


    fun setClickListener(listener: ItemClickListener) {
        this.imageClickListener = listener
    }

    fun interface ItemClickListener {
        fun onItemClick(view: View, any: Any, index: Int)
    }
    fun updateList(newList: List<User>) {
        val diffCallback = RecycleViewDiffUtil(items, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        items.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }
    fun addNewItems(newList: List<User>,callback: () -> Unit) {
        for (item in newList){
            items.add(item)
        }
        //val diffCallback = RecycleViewDiffUtil(items, newList)
        //val diffResult = DiffUtil.calculateDiff(diffCallback)
//        items.addAll(newList)
        notifyDataSetChanged()
        //diffResult.dispatchUpdatesTo(this)
        callback()
    }
    private fun emptyUserArrayList(): ArrayList<User>{
        val arrayList = ArrayList<User>()
        return arrayList
    }
}