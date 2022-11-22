package com.isu.myborutoapp.domain.repository

import com.isu.myborutoapp.domain.model.Hero

interface LocalDataSource {
    suspend fun getSelectedHero(heroId :Int) : Hero
}