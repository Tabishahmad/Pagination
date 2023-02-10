package com.example.bookapi.data.repository

import com.example.bookapi.data.database.FavouriteBookDUO
import com.example.bookapi.domain.model.Book
import com.example.bookapi.domain.repository.DBRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DBRepositoryImpl @Inject constructor(val favouriteBookDUO:FavouriteBookDUO):DBRepository {
    override suspend fun handleBookFav(book: Book) {
        try {
            println("clickDebug 3 " + book.isFav)
            if(book.isFav){
                println()
                favouriteBookDUO.removeBookFromFavourite(book)
            }else{
                favouriteBookDUO.markFavouriteBook(book)
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