package com.isu.myborutoapp.domain.use_cases.get_selected_hero

import com.isu.myborutoapp.data.repository.Repository
import com.isu.myborutoapp.domain.model.Hero

class GetSelectedHeroUseCase constructor(private val repository: Repository) {
    suspend operator fun invoke(heroId : Int) : Hero{
        return repository.getSelectedHeroes(heroId = heroId)
    }
}