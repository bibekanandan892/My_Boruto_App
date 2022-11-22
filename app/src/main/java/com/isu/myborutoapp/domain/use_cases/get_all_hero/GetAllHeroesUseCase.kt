package com.isu.myborutoapp.domain.use_cases.get_all_hero

import androidx.paging.PagingData
import com.isu.myborutoapp.data.repository.Repository
import com.isu.myborutoapp.domain.model.Hero
import kotlinx.coroutines.flow.Flow

class GetAllHeroesUseCase (
    private val repository: Repository
        ) {
    operator fun invoke(): Flow<PagingData<Hero>>{
        return repository.getAllHeroes()
    }
}