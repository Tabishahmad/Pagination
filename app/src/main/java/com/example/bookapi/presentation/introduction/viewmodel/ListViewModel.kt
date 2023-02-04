package com.example.bookapi.presentation.introduction.viewmodel

import androidx.lifecycle.ViewModel
import com.example.bookapi.domain.usecase.ListUseCase
import com.example.bookapi.domain.usecase.datamodel.Book
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.example.bookapi.domain.usecase.datamodel.IResult
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val useCase: ListUseCase) : ViewModel() {

    fun fetchList(): Flow<IResult<Book>> = flow {
        useCase().collect{result->
            when(result){
                is IResult.Success->{
                    emit(IResult.Success(result.data))
                }
                is IResult.Error ->{
                    emit(IResult.Error("Error"))
                }
                is IResult.Loading ->{
                    emit(IResult.Loading(true))
                }
            }
         }
    }


}