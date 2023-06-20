package com.witzeal.pagination.domain.usecase

import com.example.bookapi.domain.model.Book
import com.example.bookapi.domain.model.NetworkResult
import com.example.bookapi.domain.repository.BookListRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class GetRemoteListUseCase @Inject constructor (
    private val repository: BookListRepository
) {
     operator fun invoke(): Flow<NetworkResult<Book>> = flow {
        emit(repository.getBookList())
    }
}