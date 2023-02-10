package com.example.bookapi.hilt

import com.example.bookapi.data.repository.BookListRepositoryImpl
import com.example.bookapi.data.repository.remote.BookDataSource
import com.example.bookapi.domain.repository.BookListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class ViewModelModule {

    @Provides
    fun provideIListRepository(dataSource: BookDataSource): BookListRepository {
        return BookListRepositoryImpl(dataSource)
    }

}