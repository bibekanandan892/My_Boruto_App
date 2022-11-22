package com.isu.myborutoapp.domain.repository

import androidx.paging.PagingData
import androidx.room.Query
import com.isu.myborutoapp.domain.model.Hero
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getAllHero() : Flow<PagingData<Hero>>
    fun searchHeroes(query: String) : Flow<PagingData<Hero>>
}