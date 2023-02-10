package com.example.bookapi.domain.usecase

import com.example.bookapi.domain.model.Book
import com.example.bookapi.domain.repository.DBRepository
import javax.inject.Inject

class HandleBookFavUseCase @Inject constructor(val dbRepository: DBRepository) {
    suspend fun handleBookFav(book: Book){
        dbRepository.handleBookFav(book)
    }
}