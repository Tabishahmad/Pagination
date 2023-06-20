package com.witzeal.pagination.domain.repository


import com.example.bookapi.domain.model.Book
import com.example.bookapi.domain.model.NetworkResult

fun interface BookListRepository {
    suspend fun getBookList(): NetworkResult<Book>
}