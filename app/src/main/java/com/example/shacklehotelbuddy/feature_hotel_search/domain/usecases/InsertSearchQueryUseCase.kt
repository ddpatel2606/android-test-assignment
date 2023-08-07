package com.example.shacklehotelbuddy.feature_hotel_search.domain.usecases

import com.example.shacklehotelbuddy.feature_hotel_search.domain.model.SearchQuery
import com.example.shacklehotelbuddy.feature_hotel_search.domain.repository.ShackleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InsertSearchQueryUseCase @Inject constructor(
    private val shackleRepository: ShackleRepository
) {
    operator fun invoke(searchQuery: SearchQuery): Flow<Unit> = flow {
        emit(
            shackleRepository.insertSearchQuery(searchQuery)
        )
    }
}