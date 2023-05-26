package com.example.bookapi.hilt

import android.content.Context
import com.example.bookapi.data.repository.BookListRepositoryImpl
import com.example.bookapi.data.repository.remote.BookDataSource
import com.example.bookapi.domain.repository.BookListRepository
import com.example.bookapi.domain.repository.DBRepository
import com.example.bookapi.domain.usecase.UseCase
import com.example.bookapi.domain.usecase.GetListUseCase
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
                            dbRepository: DBRepository):UseCase{
        return UseCase(GetListUseCase(bookListRepository),
            ManageBookFavoriteUseCase(dbRepository))
    }
}