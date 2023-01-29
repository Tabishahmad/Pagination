package com.example.bookapi.hilt

import android.content.Context
import com.example.bookapi.MyApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun provideAppContext():Context{
        return MyApplication.context
    }
}