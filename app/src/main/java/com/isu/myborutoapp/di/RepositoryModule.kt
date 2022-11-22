package com.isu.myborutoapp.di

import android.content.Context
import com.isu.myborutoapp.data.repository.DataStoreOperationsImpl
import com.isu.myborutoapp.data.repository.Repository
import com.isu.myborutoapp.domain.repository.DataStoreOperations
import com.isu.myborutoapp.domain.use_cases.UseCases
import com.isu.myborutoapp.domain.use_cases.get_all_hero.GetAllHeroesUseCase
import com.isu.myborutoapp.domain.use_cases.get_selected_hero.GetSelectedHeroUseCase
import com.isu.myborutoapp.domain.use_cases.read_onboarding.ReadOnBoardingUseCase
import com.isu.myborutoapp.domain.use_cases.save_onboarding.SaveOnBoardingUseCase
import com.isu.myborutoapp.domain.use_cases.search_heroes.SearchHeroesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDataOperations(@ApplicationContext context: Context) : DataStoreOperations{
        return DataStoreOperationsImpl(context =  context)
    }

    @Provides
    @Singleton
    fun provideUseCases(repository: Repository): UseCases{
        return UseCases(
            saveOnBoardingUseCase = SaveOnBoardingUseCase(repository = repository),
            readOnBoardingUseCase = ReadOnBoardingUseCase(repository = repository),
            getAllHeroesUseCase = GetAllHeroesUseCase(repository = repository),
            searchHeroesUseCase = SearchHeroesUseCase(repository = repository),
            getSelectedHeroUseCase = GetSelectedHeroUseCase(repository = repository)
        )
    }
}