package com.example.bookapi.data.repository

import com.example.bookapi.data.database.FavouriteBookDUO
import com.example.bookapi.domain.model.Book
import com.example.bookapi.domain.repository.DBRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class DBRepositoryImpl @Inject constructor(val favouriteBookDUO:FavouriteBookDUO):DBRepository {
    override suspend fun handleBookFav(book: Book) {
        try {
            println("clickDebug 3 " + book.isFav)
            if(book.isFav){
                println()
                favouriteBookDUO.markFavouriteBook(book)
            }else{
                favouriteBookDUO.removeBookFromFavourite(book)
            }
        }catch (e:Exception){
            e.printStackTrace()
        }

    }
    override fun getAllBooks(): Flow<List<Book>> {
        println("clickDebug 6 " )
        return favouriteBookDUO.getAllFavouriteBook()
    }
}