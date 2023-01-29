package com.example.bookapi.data.repository.model

import com.example.bookapi.domain.usecase.datamodel.Book

data class BookDTO(
    val items: List<Item>,
    val kind: String,
    val totalItems: Int
)
fun BookDTO.toBook():List<Book>{
    val bookArray : ArrayList<Book> = ArrayList(items.size)
    for (item in items){
        item.volumeInfo?.title.let {
            val thumbNail = item.volumeInfo?.imageLinks?.thumbnail ?: ""
            bookArray.add(Book(item.volumeInfo?.title!!,thumbNail))
        }
    }
    return bookArray
}