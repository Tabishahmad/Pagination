package com.example.bookapi.domain.usecase

import com.example.bookapi.data.repository.model.StickerFramesDTO

interface IListRepository {
    suspend fun getMovieList(): StickerFramesDTO
}