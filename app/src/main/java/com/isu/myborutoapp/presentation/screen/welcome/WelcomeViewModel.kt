package com.isu.myborutoapp.presentation.screen.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isu.myborutoapp.domain.use_cases.UseCases
import com.isu.myborutoapp.domain.use_cases.read_onboarding.ReadOnBoardingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val useCase: UseCases
) : ViewModel() {
    fun saveOnBoardingState(completed : Boolean){
        viewModelScope.launch(Dispatchers.IO){
            useCase.saveOnBoardingUseCase(completed = completed)
        }
    }
}