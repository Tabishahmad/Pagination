package com.witzeal.pagination.hilt

import android.content.Context
import com.witzeal.pagination.data.repository.remote.BookListRepositoryImpl
import com.witzeal.pagination.domain.repository.BookListRepository
import com.witzeal.pagination.domain.usecase.GetRemoteListUseCase
import com.witzeal.pagination.domain.usecase.UseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class ViewModelModule {

    @Provides
    fun provideIListRepository(context: Context): BookListRepository {
        return BookListRepositoryImpl(context)
    }
    @Provides
    fun provideBookUseCase(bookListRepository: BookListRepository):UseCase{
        return UseCase(GetRemoteListUseCase(bookListRepository))
    }
}