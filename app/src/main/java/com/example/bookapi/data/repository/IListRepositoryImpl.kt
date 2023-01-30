package com.example.bookapi.data.repository

import com.example.bookapi.data.repository.model.BookDTO
import com.example.bookapi.data.repository.remote.IDataSource
import com.example.bookapi.domain.EntityMapper
import com.example.bookapi.domain.usecase.IListRepository
import com.example.bookapi.domain.usecase.datamodel.Book
import com.example.bookapi.domain.usecase.datamodel.IResult
import javax.inject.Inject

class IListRepositoryImpl @Inject
        constructor(private val apiCall: IDataSource,
                    private val entityMapper: EntityMapper<BookDTO, List<Book>>):IListRepository {
    override suspend fun getBookList(): IResult<Book> {
        try {
            val response = apiCall.downloadBookList()
            if (response.isSuccessful) {
                return IResult.Success(response.body()?.let { entityMapper.transformFrom(it) })
            } else {
                return IResult.Error("fail")
            }
        }catch (e:Exception){
            e.printStackTrace()
            return IResult.Error("fail")
        }
    }
}