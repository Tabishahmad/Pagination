package com.witzeal.pagination.domain.usecase

import com.witzeal.pagination.domain.model.User
import com.witzeal.pagination.domain.model.NetworkResult
import com.witzeal.pagination.domain.repository.BookListRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class GetRemoteListUseCase @Inject constructor (
    private val repository: BookListRepository
) {
     operator fun invoke(offset:Int): Flow<NetworkResult<User>> = flow {
        emit(repository.getBookList(offset))
    }
}