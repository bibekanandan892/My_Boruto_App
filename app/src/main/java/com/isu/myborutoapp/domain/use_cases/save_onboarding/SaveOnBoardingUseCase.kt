package com.isu.myborutoapp.domain.use_cases.save_onboarding

import com.isu.myborutoapp.data.repository.Repository

class SaveOnBoardingUseCase constructor(private val repository: Repository) {
    suspend operator fun invoke(completed : Boolean){
        repository.saveOnBoardingState(completed = completed)
    }
}