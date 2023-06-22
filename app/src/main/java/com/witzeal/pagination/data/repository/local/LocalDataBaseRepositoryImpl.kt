//package com.example.bookapi.data.repository
//
//import com.witzeal.pagination.data.database.FavouriteBookDUO
//import com.witzeal.pagination.domain.model.User
//import com.witzeal.pagination.domain.repository.LocalDataBaseRepository
//import javax.inject.Inject
//
//class DBRepositoryImpl @Inject constructor(private val favouriteBookDUO:FavouriteBookDUO):LocalDataBaseRepository {
//    override suspend fun setBookFavorite(book: User) {
//        favouriteBookDUO.markFavouriteBook(book)
//    }
//
//    override suspend fun removeBookFromFavorites(book: User) {
//        favouriteBookDUO.removeBookFromFavorites(book)
//    }
//
//    override suspend fun getBooksList(): List<User> {
//        return favouriteBookDUO.getAllFavoriteBooks()
//    }
//
//    override suspend fun getBook(bookId:String): User {
//        return favouriteBookDUO.getBook(bookId)
//    }
//
//}