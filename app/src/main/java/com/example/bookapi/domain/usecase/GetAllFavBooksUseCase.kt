package com.example.bookapi.domain.usecase

import com.example.bookapi.domain.model.Book
import com.example.bookapi.domain.repository.DBRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class GetAllFavBooksUseCase @Inject constructor(val dbRepository: DBRepository) {
    operator fun invoke():Flow<List<Book>>{
        return dbRepository.getAllBooks()
    }
}