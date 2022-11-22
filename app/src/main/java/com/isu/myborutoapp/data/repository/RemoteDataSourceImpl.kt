package com.isu.myborutoapp.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.room.Query
import com.isu.myborutoapp.data.local.BorutoDatabase
import com.isu.myborutoapp.data.paging_source.HeroRemoteMediator
import com.isu.myborutoapp.data.paging_source.SearchHeroesSource
import com.isu.myborutoapp.data.remote.BorutoApi
import com.isu.myborutoapp.domain.model.Hero
import com.isu.myborutoapp.domain.repository.RemoteDataSource
import com.isu.myborutoapp.utils.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow

class RemoteDataSourceImpl constructor(
    private val borutoApi: BorutoApi,
    private val borutoDatabase: BorutoDatabase) : RemoteDataSource {
    private val heroDao = borutoDatabase.heroDao()
    @OptIn(ExperimentalPagingApi::class)
    override fun getAllHero(): Flow<PagingData<Hero>> {
        val pagingSourceFactory = {heroDao.getAllHeroes()}
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = HeroRemoteMediator(borutoApi = borutoApi,borutoDatabase = borutoDatabase),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override fun searchHeroes(query: String): Flow<PagingData<Hero>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                SearchHeroesSource(borutoApi = borutoApi, query = query)
            }
        ).flow
    }
}