package com.example.bookapi.domain.usecase

import com.example.bookapi.data.repository.model.BookDTO

interface IListRepository {
    suspend fun getBookList(): BookDTO
}