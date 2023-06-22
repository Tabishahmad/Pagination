package com.witzeal.pagination.domain.repository

import com.witzeal.pagination.domain.model.User
import com.witzeal.pagination.domain.model.NetworkResult


fun interface BookListRepository {
    suspend fun getBookList(offset : Int): NetworkResult<User>
}