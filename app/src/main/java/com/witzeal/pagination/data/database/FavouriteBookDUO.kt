//package com.witzeal.pagination.data.database
//
//import androidx.room.*
//import com.witzeal.pagination.common.DATABASE_TABLE_NAME
//import com.witzeal.pagination.domain.model.Book
//
//
//@Dao
//interface FavouriteBookDUO {
//
//    @Query("SELECT * FROM $DATABASE_TABLE_NAME")
//    suspend fun getAllFavoriteBooks(): List<Book>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun markFavouriteBook(book: Book)
//
//    @Delete
//    suspend fun removeBookFromFavorites(book: Book)
//
//    @Query("SELECT * FROM $DATABASE_TABLE_NAME WHERE bookHashId = :bookId")
//    suspend fun getBook(bookId: String): Book
//}