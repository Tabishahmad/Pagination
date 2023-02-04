package com.example.bookapi.data.model

import com.example.bookapi.MockFileReader
import com.example.bookapi.data.repository.model.BookDTO
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.junit.Assert
import org.junit.Test

class BookItemsListResponseTest {

    @Test
    fun createMovieItemFromJson() {
        val fileName = "/ListItemsResponse.json"
        val json = MockFileReader().getResponseFromJson(fileName)
        val movieItemsListResponse = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            .adapter(BookDTO::class.java).fromJson(json)

        Assert.assertEquals(870, movieItemsListResponse?.totalItems)
        Assert.assertEquals(40, movieItemsListResponse?.items?.size)

        val bookItem = movieItemsListResponse?.items?.get(0)

        Assert.assertEquals("The Chomsky Update (RLE Linguistics A: General Linguistics)",bookItem?.volumeInfo?.title)

    }

}