package com.example.bookapi.hilt

import com.example.bookapi.data.repository.IListRepositoryImpl
import com.example.bookapi.data.repository.remote.IDataSource
import com.example.bookapi.domain.usecase.IListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class ViewModelModule {

    @Provides
    fun provideIListRepository(dataSource: IDataSource):IListRepository{
        return IListRepositoryImpl(dataSource)
    }

}