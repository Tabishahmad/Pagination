package com.example.bookapi.data.datamapper


import com.example.bookapi.data.repository.model.BookDTO
import com.example.bookapi.domain.EntityMapper
import com.example.bookapi.domain.usecase.datamodel.Book

class BookListMapper: EntityMapper<BookDTO, List<Book>>  {
    override fun transformFrom(entity: BookDTO): List<Book> {
        val bookList = mutableListOf<Book>()
        entity.items?.forEach { bookItem ->
            var title = bookItem.volumeInfo?.title
            var thumbnailUrl = bookItem.volumeInfo?.imageLinks?.thumbnail
            bookItem.apply {
                bookList.add(
                    Book(
                        bookTitle = title!!,
                        thumbnailUrl=thumbnailUrl!!
                    )
                )
            }
        }
        return bookList
    }
}