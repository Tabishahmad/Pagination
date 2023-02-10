package com.example.bookapi.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Book(
    val bookTitle:String,val thumbnailUrl:String): Parcelable
