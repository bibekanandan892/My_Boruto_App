package com.isu.myborutoapp.di

import com.isu.myborutoapp.data.local.BorutoDatabase
import com.isu.myborutoapp.data.remote.BorutoApi
import com.isu.myborutoapp.data.repository.RemoteDataSourceImpl
import com.isu.myborutoapp.domain.repository.RemoteDataSource
import com.isu.myborutoapp.utils.Constants.BASE_URL
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS).build()
    }

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val contentType = MediaType.get("application/json")
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(Json.asConverterFactory(contentType)).client(okHttpClient).build()
    }

    @Provides
    @Singleton
    fun provideBorutoApi(retrofit: Retrofit): BorutoApi{
        return retrofit.create(BorutoApi::class.java)

    }
    @Provides
    @Singleton
    fun provideRemoteDateSource(
        borutoApi: BorutoApi,borutoDatabase: BorutoDatabase
    ): RemoteDataSource{
        return RemoteDataSourceImpl(borutoApi = borutoApi,borutoDatabase= borutoDatabase)
    }
}