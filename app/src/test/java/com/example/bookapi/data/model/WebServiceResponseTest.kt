package com.example.bookapi.data.model

import com.example.bookapi.domain.usecase.datamodel.Book
import com.example.bookapi.domain.usecase.datamodel.IResult
import com.example.bookapi.domain.usecase.datamodel.Status
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito.mock

class WebServiceResponseTest {

    @Test
    fun testOnSuccess(){
        val bookResponse = mock(Book::class.java)
        var list : ArrayList<Book> = ArrayList(1)
        list.add(bookResponse)
        val success = IResult.Success(list)
        Assert.assertTrue(success is IResult.Success)
    }

    @Test
    fun testOnFailure() {
        val error = IResult.Error<Book>("Status.ERROR")
        Assert.assertTrue(error is IResult.Error)
    }
}