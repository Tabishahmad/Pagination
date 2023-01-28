package com.example.bookapi.presentation.introduction.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookapi.data.repository.model.StickerFramesDTO
import com.example.bookapi.domain.usecase.ListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.example.bookapi.domain.usecase.datamodel.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val useCase: ListUseCase) : ViewModel() {

    fun fetchList(): Flow<Result<StickerFramesDTO>> = flow {
        useCase().collect{
                emit(Result.Success(it.data))
         }
    }
}