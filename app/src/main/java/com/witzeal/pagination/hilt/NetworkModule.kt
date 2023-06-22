package com.witzeal.pagination.hilt


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }
//    @Provides
//    fun provideRetrofitClient(gsonConverterFactory: GsonConverterFactory): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl(BuildConfig.BASE_URL)
//            .addConverterFactory(gsonConverterFactory)
//            .build()
//    }

//    @Provides
//    fun provideBookApi(retrofit: Retrofit): BookDataSource {
//        return retrofit.create(BookDataSource::class.java)
//    }
}