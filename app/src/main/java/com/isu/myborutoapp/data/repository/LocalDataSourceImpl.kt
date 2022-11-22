package com.isu.myborutoapp.data.repository

import com.isu.myborutoapp.data.local.BorutoDatabase
import com.isu.myborutoapp.domain.model.Hero
import com.isu.myborutoapp.domain.repository.LocalDataSource

class LocalDataSourceImpl(borutoDatabase: BorutoDatabase) : LocalDataSource {

    private val heroDao = borutoDatabase.heroDao()

    override suspend fun getSelectedHero(heroId: Int): Hero {
        return heroDao.getSelectedHero(heroId= heroId)
    }
}