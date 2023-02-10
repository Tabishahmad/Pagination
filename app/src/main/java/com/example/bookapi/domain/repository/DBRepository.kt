package com.example.bookapi.domain.repository

import com.example.bookapi.domain.model.Book
import kotlinx.coroutines.flow.Flow


interface DBRepository {
    suspend fun handleBookFav(book: Book)
    fun getAllBooks(): Flow<List<Book>>
}