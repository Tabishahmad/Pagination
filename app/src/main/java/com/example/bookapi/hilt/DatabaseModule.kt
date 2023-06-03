package com.example.bookapi.hilt

import android.content.Context
import androidx.room.Room
import com.example.bookapi.common.DATABASE_TABLE_NAME
import com.example.bookapi.data.database.FavouriteBookDUO
import com.example.bookapi.data.database.FavouriteBookDatabase
import com.example.bookapi.data.repository.DBRepositoryImpl
import com.example.bookapi.domain.repository.LocalDataBaseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    fun provideFavouriteBookDUO(favouriteBookDatabase: FavouriteBookDatabase): FavouriteBookDUO {
        return favouriteBookDatabase.favouriteBookDUO()
    }
    @Provides
    fun provideDatabaseRepo(favouriteBookDUO: FavouriteBookDUO):LocalDataBaseRepository{
        return DBRepositoryImpl(favouriteBookDUO)
    }
    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context): FavouriteBookDatabase {
        return Room.databaseBuilder(
            appContext,
            FavouriteBookDatabase::class.java,
            DATABASE_TABLE_NAME
        ).build()
    }
}