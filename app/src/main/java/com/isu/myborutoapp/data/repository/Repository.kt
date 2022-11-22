package com.isu.myborutoapp.data.repository

import androidx.paging.PagingData
import androidx.room.Query
import com.isu.myborutoapp.domain.model.Hero
import com.isu.myborutoapp.domain.repository.DataStoreOperations
import com.isu.myborutoapp.domain.repository.LocalDataSource
import com.isu.myborutoapp.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remote : RemoteDataSource,
    private val dataStore: DataStoreOperations,
) {
    fun getAllHeroes(): Flow<PagingData<Hero>>{
        return remote.getAllHero()
    }
    suspend fun getSelectedHeroes(heroId : Int): Hero{
        return localDataSource.getSelectedHero(heroId = heroId)
    }
    suspend fun saveOnBoardingState(completed : Boolean){
        dataStore.saveOnBoardingState(completed = completed)
    }
    fun readOnBoardingState(): Flow<Boolean>{
        return dataStore.readOnBoardingState()
    }

    fun searchHeroes(query: String) : Flow<PagingData<Hero>>{
        return remote.searchHeroes(query = query)
    }
}