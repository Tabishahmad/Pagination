package com.witzeal.pagination.hilt

import android.content.Context
import com.example.bookapi.data.repository.remote.BookListRepositoryImpl
import com.example.bookapi.data.repository.remote.BookDataSource
import com.example.bookapi.domain.repository.BookListRepository
import com.example.bookapi.domain.repository.LocalDataBaseRepository
import com.example.bookapi.domain.usecase.UseCase
import com.example.bookapi.domain.usecase.GetRemoteListUseCase
import com.example.bookapi.domain.usecase.ManageBookFavoriteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class ViewModelModule {

    @Provides
    fun provideIListRepository(dataSource: BookDataSource,context: Context): BookListRepository {
        return BookListRepositoryImpl(dataSource,context)
    }
    @Provides
    fun provideBookUseCase(bookListRepository: BookListRepository,
                            dbRepository: LocalDataBaseRepository):UseCase{
        return UseCase(GetRemoteListUseCase(bookListRepository),
            ManageBookFavoriteUseCase(dbRepository))
    }
}