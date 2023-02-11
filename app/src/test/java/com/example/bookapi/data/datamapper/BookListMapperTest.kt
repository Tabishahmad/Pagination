//package com.example.bookapi.data.datamapper
//
//import com.example.bookapi.MockFileReader
//import com.example.bookapi.data.repository.model.BookDTO
//import com.squareup.moshi.Moshi
//import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
//import org.junit.Assert
//import org.junit.Test
//
//internal class BookListMapperTest {
//    @Test
//    fun testTransformFrom() {
//        val bookListMapperTest = BookListMapper()
//        val fileName = "/ListItemsResponse.json"
//        val json = MockFileReader().getResponseFromJson(fileName)
//        val movieItemsListResponse = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
//            .adapter(BookDTO::class.java).fromJson(json)
//        movieItemsListResponse?.let {
//            val bookList = bookListMapperTest.transformFrom(it)
//            Assert.assertEquals(bookList.size, it.items?.size)
//            Assert.assertEquals(it.items?.get(0)?.volumeInfo?.title, bookList.get(0).bookTitle)
//        }
//    }
//}