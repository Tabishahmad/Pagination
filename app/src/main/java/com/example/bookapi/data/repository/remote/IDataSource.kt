package com.example.bookapi.data.repository.remote

import com.example.bookapi.data.repository.model.StickerFramesDTO
import retrofit2.http.GET

interface IDataSource {
    @GET("file/0tn8muvffjzq3kp/textEditor.txt/file")
    suspend fun getFrameSticker() : StickerFramesDTO
}