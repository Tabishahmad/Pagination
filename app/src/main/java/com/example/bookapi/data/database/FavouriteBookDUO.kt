package com.example.bookapi.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.bookapi.common.TABLE_NAME
import com.example.bookapi.domain.model.Book
import com.example.bookapi.domain.model.FavouriteBookEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface FavouriteBookDUO {
    @Query("SELECT * FROM "+ TABLE_NAME)
    fun getAllFavouriteBook(): Flow<List<FavouriteBookEntity>>

    @Insert
    fun markFavouriteBook(favouriteBookEntity: FavouriteBookEntity)

}