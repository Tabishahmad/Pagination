package com.example.bookapi.presentation.introduction.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookapi.data.datamapper.RoomNRemoteMerger
import com.example.bookapi.domain.model.Book
import com.example.bookapi.domain.model.NetworkResult
import com.example.bookapi.domain.usecase.BookListUseCase
import com.example.bookapi.presentation.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@HiltViewModel
class ListViewModel @Inject constructor(private val bookListUseCase: BookListUseCase) : ViewModel() {

    private val uiStateFlow =
        MutableStateFlow<ViewState<List<Book>>>(ViewState.Loading(true))

    @Inject
    lateinit var roomNRemoteMerger: RoomNRemoteMerger


    fun fetchList() {
        viewModelScope.launch {
            bookListUseCase.getListUseCase().collect { result ->
                when (result) {
                    is NetworkResult.Success -> {
                        val savedBook = getAllFavouriteBooks()
                        val listWithFav = roomNRemoteMerger.checkListNMarkFavorite(savedBook,result.data)
                        uiStateFlow.emit(ViewState.Success(listWithFav))
                    }
                    is NetworkResult.Failure ->
                        uiStateFlow.emit(ViewState.Failure(result.throwable))
                }
            }
        }
    }


    fun handleBookFav(book: Book){
        viewModelScope.launch {
            bookListUseCase.markFavUseCase.handleBookFav(book)
        }
    }
    private suspend fun getAllFavouriteBooks():List<Book>{
        return suspendCancellableCoroutine {sc->
            viewModelScope.launch {
                bookListUseCase.getAllFavBooksUseCase().collect {
                    if(sc.isActive)sc.resume(it)
                }
            }
        }
    }
    fun getViewStateFlow(): StateFlow<ViewState<List<Book>>> = uiStateFlow
}