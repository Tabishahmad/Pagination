package com.example.bookapi.domain.usecase

data class BookListUseCase(val getListUseCase: GetListUseCase
                           , val markFavUseCase: HandleBookFavUseCase,
                              val getAllFavBooksUseCase: GetAllFavBooksUseCase) {
}