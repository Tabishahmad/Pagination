package com.example.bookapi.data.repository

import com.example.bookapi.data.repository.model.StickerFramesDTO
import com.example.bookapi.data.repository.remote.IDataSource
import com.example.bookapi.domain.usecase.IListRepository
import javax.inject.Inject

class IListRepositoryImpl @Inject constructor(val apiCall: IDataSource):IListRepository {
    override suspend fun getMovieList(): StickerFramesDTO {
        return apiCall.getFrameSticker()
    }
}