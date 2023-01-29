package com.example.bookapi.domain.usecase

import com.example.bookapi.data.repository.model.BookDTO
import com.example.bookapi.domain.usecase.datamodel.IResult

interface IListRepository {
    suspend fun getBookList(): IResult<BookDTO>
}