package com.witzeal.pagination.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val rank: Int,
    val profile_pic_URL: String,
    val user_name : String,
    val user_point:Int,
    val price_money: Int): Parcelable