package com.example.bookapi.data.repository.model

data class StickerFramesDTO(
    val frame: List<String>,
    val bg_texture: List<String>,
    val sm_id: Int,
    val sticker: List<String>,
    val quotes : List<String>,
    val ver: Int
)
fun StickerFramesDTO.toDomainSticker(): ArrayList<String>{
    return ArrayList(sticker)
}
fun StickerFramesDTO.toDomainFrame(): ArrayList<String>{
    return ArrayList(frame)
}
fun StickerFramesDTO.toDomainBGTexture(): ArrayList<String>{
    return ArrayList(bg_texture)
}
fun StickerFramesDTO.toDomainMessage(): ArrayList<String>{
    return ArrayList(quotes)
}