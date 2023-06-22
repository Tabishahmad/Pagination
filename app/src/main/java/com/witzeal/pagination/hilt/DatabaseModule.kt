//package com.witzeal.pagination.hilt
//
//import android.content.Context
//import androidx.room.Room
//import com.example.bookapi.data.repository.DBRepositoryImpl
//import com.witzeal.pagination.common.DATABASE_TABLE_NAME
//import com.witzeal.pagination.data.database.FavouriteBookDUO
//import com.witzeal.pagination.data.database.FavouriteBookDatabase
//import com.witzeal.pagination.domain.repository.LocalDataBaseRepository
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.android.qualifiers.ApplicationContext
//import dagger.hilt.components.SingletonComponent
//
//@InstallIn(SingletonComponent::class)
//@Module
//class DatabaseModule {
//    @Provides
//    fun provideFavouriteBookDUO(favouriteBookDatabase: FavouriteBookDatabase): FavouriteBookDUO {
//        return favouriteBookDatabase.favouriteBookDUO()
//    }
//    @Provides
//    fun provideDatabaseRepo(favouriteBookDUO: FavouriteBookDUO):LocalDataBaseRepository{
//        return DBRepositoryImpl(favouriteBookDUO)
//    }
//    @Provides
//    fun provideAppDatabase(@ApplicationContext appContext: Context): FavouriteBookDatabase {
//        return Room.databaseBuilder(
//            appContext,
//            FavouriteBookDatabase::class.java,
//            DATABASE_TABLE_NAME
//        ).build()
//    }
//}