package com.example.bookapi.hilt

import com.example.bookapi.data.repository.IListRepositoryImpl
import com.example.bookapi.data.repository.model.BookDTO
import com.example.bookapi.data.repository.remote.IDataSource
import com.example.bookapi.domain.EntityMapper
import com.example.bookapi.domain.usecase.IListRepository
import com.example.bookapi.domain.usecase.datamodel.Book
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class ViewModelModule {

    @Provides
    fun provideIListRepository(dataSource: IDataSource,
                               @BookListMappingAnnotation
                                mapper: EntityMapper<BookDTO, List<Book>>):IListRepository{
        return IListRepositoryImpl(dataSource,mapper)
    }

}