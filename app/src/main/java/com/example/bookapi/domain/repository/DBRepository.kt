package com.example.bookapi.domain.repository

import com.example.bookapi.domain.model.Book
import kotlinx.coroutines.flow.Flow


interface DBRepository {
    suspend fun setBookFavorite(book: Book)
    suspend fun removeBookFromFavorites(book: Book)
    fun getBooksList(): Flow<List<Book>>
    suspend fun getBook(bookId: String):Book?
}