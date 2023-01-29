package com.example.bookapi.domain.usecase

import com.example.bookapi.data.repository.model.BookDTO
import com.example.bookapi.domain.usecase.datamodel.IResult
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class ListUseCase @Inject constructor (
    private val repository: IListRepository
) {
    suspend operator fun invoke(): Flow<IResult<BookDTO>> = flow {
        emit(repository.getBookList())
    }
}