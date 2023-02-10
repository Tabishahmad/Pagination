package com.example.bookapi.presentation.introduction.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookapi.domain.model.Book
import com.example.bookapi.domain.model.NetworkResult
import com.example.bookapi.domain.usecase.BookListUseCase
import com.example.bookapi.presentation.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val bookListUseCase: BookListUseCase) : ViewModel() {

    private val uiStateFlow =
        MutableStateFlow<ViewState<List<Book>>>(ViewState.Loading(true))

    fun fetchList() {
        viewModelScope.launch {
            bookListUseCase.getListUseCase().collect { result ->
                println("clickDebug 7 " )
                when (result) {
                    is NetworkResult.Success -> {
                        // is it ok ??
                        println("clickDebug 4 " )
                        getAllFavouriteBooks(result.data)
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
    // I have a doubt in this method
    // there should be some better way to this
    private fun getAllFavouriteBooks(listOfBook : List<Book>){
        viewModelScope.launch {
            var hashSetBook : HashSet<String> = HashSet()
            bookListUseCase.getAllFavBooksUseCase().collect {
                // can we avoid two loop
                println("clickDebug 5 " )
                for (book in it){
                    hashSetBook.add(book.bookHashId)
                }
                for (book in listOfBook){
                    if(hashSetBook.contains(book.bookHashId)){
                        book.isFav = true
                    }
                }
                uiStateFlow.emit(ViewState.Success(listOfBook))
            }
        }
    }

    fun getViewStateFlow(): StateFlow<ViewState<List<Book>>> = uiStateFlow
}