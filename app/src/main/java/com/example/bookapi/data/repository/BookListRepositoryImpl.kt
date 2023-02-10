package com.example.bookapi.data.repository

import com.example.bookapi.data.repository.remote.BookDataSource
import com.example.bookapi.domain.repository.BookListRepository
import com.example.bookapi.domain.model.Book
import com.example.bookapi.domain.model.NetworkResult
import javax.inject.Inject

class BookListRepositoryImpl @Inject
        constructor(private val apiCall: BookDataSource): BookListRepository {

    override suspend fun getBookList(): NetworkResult<Book> {
        try {
            val response = apiCall.downloadBookList()
            if (response.isSuccessful) {
                return NetworkResult.Success(response.body()!!.toBook())
            } else {
                return NetworkResult.Failure(throw Exception())
            }
        }catch (e:Exception){
            e.printStackTrace()
            return NetworkResult.Failure(throw Exception())
        }
    }
}