package com.example.bookapi.data.repository

import com.example.bookapi.domain.model.Book
import com.example.bookapi.domain.repository.DBRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeDBRepository : DBRepository {
    private val fakeBookList = mutableListOf<Book>()

    override suspend fun setBookFavorite(book: Book) {
        book.isFav = !book.isFav
    }

    override suspend fun removeBookFromFavorites(book: Book) {
        fakeBookList.remove(book)
    }

    override fun getBooksList(): Flow<List<Book>> {
        return flow { emit(fakeBookList) }
    }

    override suspend fun getBook(bookId: String): Book? {
        return fakeBookList.find { it.bookHashId == bookId }
    }
}