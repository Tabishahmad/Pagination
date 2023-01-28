package com.example.bookapi.domain.usecase

import com.example.bookapi.data.repository.model.StickerFramesDTO
import com.example.bookapi.domain.usecase.datamodel.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ListUseCase @Inject constructor (
    private val repository: IListRepository
) {
    suspend operator fun invoke(): Flow<Result<StickerFramesDTO>> = flow {
            emit(Result.Success(repository.getMovieList()))
    }
}