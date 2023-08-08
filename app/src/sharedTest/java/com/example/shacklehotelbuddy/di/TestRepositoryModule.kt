package com.example.shacklehotelbuddy.di

import com.example.shacklehotelbuddy.feature_hotel_search.data.repository_impl.ShackleRepositoryImplTest
import com.example.shacklehotelbuddy.feature_hotel_search.domain.repository.ShackleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton


/**
 *  TestRepositoryModule
 *  This is a TestRepository Module, It will replace the RepositoryModule for testing purpose.
 */
@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RepositoryModule::class]
)
object TestRepositoryModule {

    @Provides
    @Singleton
    fun provideShackleRepository(): ShackleRepository {
        return ShackleRepositoryImplTest()
    }
}