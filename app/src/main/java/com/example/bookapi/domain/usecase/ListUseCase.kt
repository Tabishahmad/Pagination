package com.example.bookapi.domain.usecase

import com.example.bookapi.domain.usecase.datamodel.Book
import com.example.bookapi.domain.usecase.datamodel.IResult
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class ListUseCase @Inject constructor (
    private val repository: IListRepository
) {
    suspend operator fun invoke(): Flow<IResult<Book>> = flow {
        emit(repository.getBookList())
    }
}