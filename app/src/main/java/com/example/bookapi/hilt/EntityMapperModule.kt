package com.llyods.assignment.di


import com.example.bookapi.data.datamapper.BookListMapper
import com.example.bookapi.data.repository.model.BookDTO
import com.example.bookapi.domain.EntityMapper
import com.example.bookapi.domain.usecase.datamodel.Book
import com.example.bookapi.hilt.BookListMappingAnnotation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object EntityMapperModule {

    @Provides
    @BookListMappingAnnotation
    fun provideEntityMapper(): EntityMapper<BookDTO, List<Book>> =
        BookListMapper()
}