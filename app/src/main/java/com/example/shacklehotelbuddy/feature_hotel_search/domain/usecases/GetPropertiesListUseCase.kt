package com.example.shacklehotelbuddy.feature_hotel_search.domain.usecases

import com.example.shacklehotelbuddy.feature_hotel_search.domain.repository.ShackleRepository
import com.example.shacklehotelbuddy.feature_hotel_search.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPropertiesListUseCase @Inject constructor(
    private val shackleRepository: ShackleRepository
) {
    operator fun invoke(
        checkedInDate: String,
        checkedOutDate: String,
        adults: Int,
        children: Int
    ): Flow<Resource<Any>> = flow {
        emit(
            shackleRepository.getPropertiesList(checkedInDate,checkedOutDate,adults,children)
        )
    }
}