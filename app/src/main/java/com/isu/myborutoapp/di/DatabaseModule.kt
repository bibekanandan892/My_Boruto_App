package com.isu.myborutoapp.di

import android.content.Context
import androidx.room.Room
import com.isu.myborutoapp.data.local.BorutoDatabase
import com.isu.myborutoapp.data.repository.LocalDataSourceImpl
import com.isu.myborutoapp.domain.repository.LocalDataSource
import com.isu.myborutoapp.utils.Constants.BORUTO_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
    ) = Room.databaseBuilder(
            context,
            BorutoDatabase::class.java,
            BORUTO_DATABASE)
            .build()

    @Provides
    @Singleton
    fun provideLocalDataSource(borutoDatabase: BorutoDatabase) : LocalDataSource{
        return LocalDataSourceImpl(borutoDatabase = borutoDatabase)
    }

}