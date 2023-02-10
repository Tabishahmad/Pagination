package com.example.bookapi.domain.usecase

import com.example.bookapi.domain.model.Book
import com.example.bookapi.domain.repository.DBRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllFavBooksUseCase @Inject constructor(val dbRepository: DBRepository) {
    suspend operator fun invoke():Flow<List<Book>>{
        return dbRepository.getAllBooks()
    }
}