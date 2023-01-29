package com.example.bookapi.data.repository

import com.example.bookapi.data.repository.model.BookDTO
import com.example.bookapi.data.repository.remote.IDataSource
import com.example.bookapi.domain.usecase.IListRepository
import com.example.bookapi.domain.usecase.datamodel.IResult
import javax.inject.Inject

class IListRepositoryImpl @Inject constructor(val apiCall: IDataSource):IListRepository {
    override suspend fun getBookList(): IResult<BookDTO> {
        val response = apiCall.downloadBookList()
        if(response.isSuccessful){
            return IResult.Success(response.body())
        }else{
            return IResult.Error("fail")
        }
    }
}