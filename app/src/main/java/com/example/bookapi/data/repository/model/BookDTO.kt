package com.example.bookapi.data.repository.model

import com.example.bookapi.domain.model.Book

data class BookDTO(
    val items: List<Item>,
    val kind: String,
    val totalItems: Int){

    fun toBook():List<Book>{
        val bookArray : ArrayList<Book> = ArrayList(items.size)

//        items.associateBy { it.id }
//        val bookArray  = items.groupingBy { it.id }
//        var book
        for (item in items){
            item.volumeInfo?.title.let {
                val thumbNail = item.volumeInfo?.imageLinks?.thumbnail ?: ""
                bookArray.add(Book(item.id,item.volumeInfo?.title!!,thumbNail))
            }
        }
        return bookArray
    }
}
