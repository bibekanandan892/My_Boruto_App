package com.isu.myborutoapp.domain.use_cases.read_onboarding

import com.isu.myborutoapp.data.repository.Repository
import kotlinx.coroutines.flow.Flow

class ReadOnBoardingUseCase constructor(private val repository: Repository) {
    operator fun invoke(): Flow<Boolean> {
        return repository.readOnBoardingState()
    }
}