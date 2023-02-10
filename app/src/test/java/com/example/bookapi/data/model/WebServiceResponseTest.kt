package com.example.bookapi.data.model

import com.example.bookapi.domain.model.Book
import com.example.bookapi.domain.model.NetworkResult
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito.mock

class WebServiceResponseTest {

    @Test
    fun testOnSuccess(){
        val bookResponse = mock(Book::class.java)
        var list : ArrayList<Book> = ArrayList(1)
        list.add(bookResponse)
        val success = NetworkResult.Success(list)
        Assert.assertTrue(success is NetworkResult.Success)
    }
//
    @Test
    fun testOnFailure() {
        val error = NetworkResult.Failure(Throwable())
        Assert.assertTrue(error is NetworkResult.Failure)
    }
}