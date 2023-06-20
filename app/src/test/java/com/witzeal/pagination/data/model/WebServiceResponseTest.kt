package com.witzeal.pagination.data.model

import com.example.bookapi.domain.model.Book
import com.example.bookapi.domain.model.NetworkResult
import org.hamcrest.CoreMatchers
import org.junit.Test
import org.mockito.Mockito.mock
import org.junit.Assert.*

class WebServiceResponseTest {

    @Test
    fun testOnSuccess(){
        val book = mock(Book::class.java)
        var list : ArrayList<Book> = ArrayList(1)
        list.add(book)
        val success = NetworkResult.Success(list)
        assertThat(success, CoreMatchers.instanceOf(NetworkResult.Success::class.java))
    }

    @Test
    fun testOnFailure() {
        val error = NetworkResult.Failure("")
        assertThat(error, CoreMatchers.instanceOf(NetworkResult.Failure::class.java))
    }
}