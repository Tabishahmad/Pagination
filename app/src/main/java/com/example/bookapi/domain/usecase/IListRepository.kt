package com.example.bookapi.domain.usecase


import com.example.bookapi.domain.usecase.datamodel.Book
import com.example.bookapi.domain.usecase.datamodel.IResult

interface IListRepository {
    suspend fun getBookList(): IResult<Book>
}