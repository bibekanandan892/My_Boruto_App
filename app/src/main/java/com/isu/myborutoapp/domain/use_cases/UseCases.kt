package com.isu.myborutoapp.domain.use_cases

import com.isu.myborutoapp.domain.use_cases.get_all_hero.GetAllHeroesUseCase
import com.isu.myborutoapp.domain.use_cases.get_selected_hero.GetSelectedHeroUseCase
import com.isu.myborutoapp.domain.use_cases.read_onboarding.ReadOnBoardingUseCase
import com.isu.myborutoapp.domain.use_cases.save_onboarding.SaveOnBoardingUseCase
import com.isu.myborutoapp.domain.use_cases.search_heroes.SearchHeroesUseCase

data class UseCases constructor(
    val saveOnBoardingUseCase: SaveOnBoardingUseCase,
    val readOnBoardingUseCase: ReadOnBoardingUseCase,
    val getAllHeroesUseCase: GetAllHeroesUseCase,
    val searchHeroesUseCase: SearchHeroesUseCase,
    val getSelectedHeroUseCase: GetSelectedHeroUseCase
) {
}