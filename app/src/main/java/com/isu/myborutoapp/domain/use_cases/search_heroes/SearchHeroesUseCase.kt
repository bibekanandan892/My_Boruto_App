package com.isu.myborutoapp.domain.use_cases.search_heroes

import androidx.paging.PagingData
import androidx.room.Query
import com.isu.myborutoapp.data.repository.Repository
import com.isu.myborutoapp.domain.model.Hero
import kotlinx.coroutines.flow.Flow

class SearchHeroesUseCase(private val repository: Repository) {
    operator fun invoke(query: String) : Flow<PagingData<Hero>>{
        return repository.searchHeroes(query = query)
    }
}