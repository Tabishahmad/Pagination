//package com.witzeal.pagination.domain.usecase
//
//import com.witzeal.pagination.domain.model.User
//import com.witzeal.pagination.domain.repository.LocalDataBaseRepository
//import javax.inject.Inject
//
//class ManageBookFavoriteUseCase @Inject constructor(val dbRepository: LocalDataBaseRepository) {
//    suspend fun setBookFavorite(book: User){
//        dbRepository.setBookFavorite(book)
//    }
//    suspend fun removeBookFromFavorites(book: User){
//        dbRepository.removeBookFromFavorites(book)
//    }
//    suspend fun isFavoriteBook(book: User): Boolean {
//        return dbRepository.getBook(book.bookHashId) != null
//    }
//    suspend fun getBooksList(): List<User> {
//        return dbRepository.getBooksList()
//    }
//}