package com.example.bookapi.data.repository

import com.example.bookapi.data.database.FavouriteBookDUO
import com.example.bookapi.domain.model.Book
import com.example.bookapi.domain.model.FavouriteBookEntity
import com.example.bookapi.domain.repository.DBRepository
import javax.inject.Inject

class DBRepositoryImpl @Inject constructor(val favouriteBookDUO:FavouriteBookDUO):DBRepository {
    override fun markFavoriteBook(favouriteBookEntity: FavouriteBookEntity) = favouriteBookDUO.markFavouriteBook(favouriteBookEntity)
}