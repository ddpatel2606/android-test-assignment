package com.example.shacklehotelbuddy.di

import com.example.shacklehotelbuddy.feature_hotel_search.data.repository_impl.ShackleRepositoryImpl
import com.example.shacklehotelbuddy.feature_hotel_search.domain.repository.ShackleRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindShackleRepository(repository: ShackleRepositoryImpl): ShackleRepository
}