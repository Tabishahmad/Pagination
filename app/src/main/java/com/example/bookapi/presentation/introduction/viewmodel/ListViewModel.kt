package com.example.bookapi.presentation.introduction.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookapi.domain.usecase.GetListUseCase
import com.example.bookapi.domain.model.Book
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.bookapi.domain.model.NetworkResult
import com.example.bookapi.presentation.ViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val getListUseCase: GetListUseCase) : ViewModel() {

    private val uiStateFlow =
        MutableStateFlow<ViewState<List<Book>>>(ViewState.Loading(true))

    fun fetchList() {
        viewModelScope.launch {
            getListUseCase().collect { result ->
                when (result) {
                    is NetworkResult.Success ->
                        uiStateFlow.emit(ViewState.Success(result.data))
                    is NetworkResult.Failure ->
                        uiStateFlow.emit(ViewState.Failure(result.throwable))
                }
            }
        }
    }

    fun getViewStateFlow(): StateFlow<ViewState<List<Book>>> = uiStateFlow
}