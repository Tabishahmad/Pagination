package com.example.bookapi.data.repository

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.bookapi.MainCoroutineRule
import com.example.bookapi.MockFileReader
import com.example.bookapi.data.datamapper.BookListMapper
import com.example.bookapi.data.repository.model.BookDTO
import com.example.bookapi.data.repository.remote.IDataSource
import com.example.bookapi.domain.usecase.datamodel.IResult
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.IOException

class BooApiTest {

    lateinit var mockWebServer: MockWebServer
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var webClient: IDataSource

    @Before
    fun initService(){
        mockWebServer = MockWebServer()
        mockWebServer.start()

//        val moshi =  GsonConverterFactory.Builder()
//            .add(KotlinJsonAdapterFactory())
//            .build()

        val moshi = GsonConverterFactory.create()

        webClient = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(moshi)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(IDataSource::class.java)
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
            val movieListMapperTest = BookListMapper()

            val movieItemsListResponse = movieListItemResponse.body()
                ?.let { movieListMapperTest.transformFrom(it) }



//            val movieList = IResult.Success(movieItemsListResponse)

//            Assert.assertEquals(movieList.data?.get(0)?.bookTitle, "tt1745960")
            Assert.assertEquals(movieItemsListResponse?.size, 40)
        }
    }

    @After
    fun tearDown(){
        mockWebServer.shutdown()
    }
}