package com.example.bookapi.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bookapi.domain.model.Book

@Database(entities = [Book::class], version = 1)
abstract class FavouriteBookDatabase : RoomDatabase() {
    abstract fun favouriteBookDUO(): FavouriteBookDUO
}