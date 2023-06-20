package com.witzeal.pagination.data.repository

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.bookapi.MainCoroutineRule
import com.example.bookapi.MockFileReader
import com.example.bookapi.data.repository.remote.BookDataSource
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class BooApiTest {

    lateinit var mockWebServer: MockWebServer
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var webClient: BookDataSource

    @Before
    fun initService(){
        mockWebServer = MockWebServer()
        mockWebServer.start()

        val moshi = GsonConverterFactory.create()

        webClient = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(moshi)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(BookDataSource::class.java)
    }

    @Throws(IOException::class)
    fun mockResponseFromJson(fileName: String) {
        val mockResponse = MockResponse()
        mockWebServer.enqueue(
            mockResponse.setBody(
                MockFileReader().getResponseFromJson(fileName)
            )
        )
    }

    @Test
    fun testMovieListFromServer() {
        runBlocking {
            mockResponseFromJson("/ListItemsResponse.json")
            val movieListItemResponse = webClient.downloadBookList()

            val movieItemsListResponse = movieListItemResponse.body()?.toBook()

            Assert.assertEquals(movieItemsListResponse?.size, 40)
        }
    }

    @After
    fun tearDown(){
        mockWebServer.shutdown()
    }
}