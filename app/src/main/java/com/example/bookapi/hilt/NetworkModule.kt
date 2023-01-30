package com.example.bookapi.hilt


import com.example.bookapi.BuildConfig
import com.example.bookapi.comman.Constants
import com.example.bookapi.data.repository.remote.IDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }
//    @Provides
//    fun provideMoshiConverterFactory(): MoshiConverterFactory = MoshiConverterFactory.create()

    @Provides
    fun provideRetrofitClient(gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    fun provideBookApi(retrofit: Retrofit): IDataSource {
        return retrofit.create(IDataSource::class.java)
    }

//    @Provides
//    fun provideInterceptor(): Interceptor = HttpLoggingInterceptor().apply {
//        level = HttpLoggingInterceptor.Level.BODY
//    }

//    @Provides
//    fun provideOKHttpClient(interceptor: Interceptor) = OkHttpClient().apply {
//        OkHttpClient.Builder().apply {
//            callTimeout(Constants.CALL_TIMEOUT, TimeUnit.SECONDS)
//            connectTimeout(Constants.CONNECT_TIMEOUT, TimeUnit.SECONDS)
//            readTimeout(Constants.READ_TIMEOUT, TimeUnit.SECONDS)
//            writeTimeout(Constants.WRITE_TIMEOUT, TimeUnit.SECONDS)
//            addInterceptor(interceptor)
//            build()
//        }
//    }
}