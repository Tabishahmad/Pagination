package com.example.bookapi.presentation.introduction.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.bookapi.R
import com.example.bookapi.domain.usecase.ListUseCase
import com.example.bookapi.domain.usecase.datamodel.Book
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.example.bookapi.domain.usecase.datamodel.IResult
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val useCase: ListUseCase,
private val context: Context) : ViewModel() {

    fun fetchList(): Flow<IResult<Book>> = flow {
        useCase().collect{result->
            when(result){
                is IResult.Success->{
                    emit(IResult.Success(data = result.data))
                }
                is IResult.Error->{
                    emit(IResult.Error(message = context.getString(R.string.response_error)))
                }
                is IResult.Loading->{
                    emit(IResult.Loading(true))
                }
            }
         }
    }
}