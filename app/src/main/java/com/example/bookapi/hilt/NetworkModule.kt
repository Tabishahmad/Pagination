package com.example.bookapi.hilt


import com.example.bookapi.BuildConfig
import com.example.bookapi.data.repository.IListRepositoryImpl
import com.example.bookapi.data.repository.remote.IDataSource
import com.example.bookapi.domain.usecase.IListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    fun provideRetrofitClient(gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    fun provideBookApi(retrofit: Retrofit): IDataSource {
        return  retrofit.create(IDataSource::class.java)
    }

    @CoroutineScopeIO
    @Provides
    fun provideCoroutineDispatcherIO() = Dispatchers.IO

    @CoroutineScopeDefault
    @Provides
    fun provideCoroutineDispatcherDefault() = Dispatchers.Default

}