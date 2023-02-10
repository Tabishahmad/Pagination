package com.example.bookapi.presentation.introduction.viewmodel

import com.example.bookapi.MockFileReader
import com.example.bookapi.data.datamapper.BookListMapper
import com.example.bookapi.data.repository.model.BookDTO
import com.example.bookapi.domain.usecase.GetListUseCase
import com.example.bookapi.domain.model.Book
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


internal class ListViewModelTest {

    private lateinit var viewModel: ListViewModel

    @Mock
    private lateinit var useCase: GetListUseCase



    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = ListViewModel(useCase)
    }

    private fun getListRespose(): List<Book> {
        val movieListMapperTest = BookListMapper()
        val fileName = "/ListItemsResponse.json"
        val json = MockFileReader().getResponseFromJson(fileName)

        val movieItemsListResponse = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            .adapter(BookDTO::class.java).fromJson(json)

        movieItemsListResponse?.let {
            return movieListMapperTest.transformFrom(it)
        }
        return emptyList()
    }
    @Test
    fun fetchListSuccessTest() = runBlocking {
        val flow = flow{
            emit(IResult.Success(getListRespose()))
        }

        Mockito.`when`(useCase()).thenReturn(flow)

        viewModel.fetchList()

        flow.collect {
            Assert.assertEquals(40, it.data?.size )
            Assert.assertEquals("The Chomsky Update (RLE Linguistics A: General Linguistics)", it.data?.get(0)?.bookTitle)
        }
    }

    @Test
//    fun fetchMovieLisLoadingTest() = runBlocking {
//        val flow = flow{
//            emit(IResult.Success(emptyList<Book>()))
//        }
//
//        Mockito.`when`(useCase()).thenReturn(flow)
//
//        viewModel.fetchList()
//
////        Assert.assertEquals(
////            ViewState.Loading(true), viewModel.getViewStateFlow().value)
//    }
    @After
    fun tearDown() {
    }
}