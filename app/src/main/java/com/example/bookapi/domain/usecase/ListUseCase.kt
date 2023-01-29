package com.example.bookapi.domain.usecase

import com.example.bookapi.data.repository.model.BookDTO
import com.example.bookapi.domain.usecase.datamodel.Result
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class ListUseCase @Inject constructor (
    private val repository: IListRepository
) {
    suspend operator fun invoke(): Flow<Result<BookDTO>> = flow {
        emit(Result.Success(repository.getBookList()))
    }
}