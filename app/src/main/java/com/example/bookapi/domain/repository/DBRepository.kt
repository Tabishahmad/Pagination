package com.example.bookapi.domain.repository

import com.example.bookapi.domain.model.FavouriteBookEntity

interface DBRepository {
    fun markFavoriteBook(favouriteBookEntity: FavouriteBookEntity)
}