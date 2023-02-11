package com.example.bookapi.hilt

import com.example.bookapi.data.datamapper.RoomNRemoteMerger
import com.example.bookapi.data.repository.BookListRepositoryImpl
import com.example.bookapi.data.repository.remote.BookDataSource
import com.example.bookapi.domain.repository.BookListRepository
import com.example.bookapi.domain.repository.DBRepository
import com.example.bookapi.domain.usecase.BookListUseCase
import com.example.bookapi.domain.usecase.GetAllFavBooksUseCase
import com.example.bookapi.domain.usecase.GetListUseCase
import com.example.bookapi.domain.usecase.HandleBookFavUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
class ViewModelModule {

    @Provides
    fun provideIListRepository(dataSource: BookDataSource): BookListRepository {
        return BookListRepositoryImpl(dataSource)
    }
    @Provides
    fun provideBookUseCase(bookListRepository: BookListRepository,
                            dbRepository: DBRepository):BookListUseCase{
        return BookListUseCase(GetListUseCase(bookListRepository),
            HandleBookFavUseCase(dbRepository), GetAllFavBooksUseCase(dbRepository))
    }
    @Provides
    fun provideRoomNRemoteMerger():RoomNRemoteMerger{
        return RoomNRemoteMerger()
    }

}