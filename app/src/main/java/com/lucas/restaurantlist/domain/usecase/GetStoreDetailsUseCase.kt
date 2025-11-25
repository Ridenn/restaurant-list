package com.lucas.restaurantlist.domain.usecase

import com.lucas.restaurantlist.data.model.StoreDetailsResponse
import com.lucas.restaurantlist.data.model.StoreResponse
import com.lucas.restaurantlist.domain.repository.StoreDetailsRepository
import com.lucas.restaurantlist.domain.repository.StoreFeedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Use case for retrieving the store details.
 */
class GetStoreDetailsUseCase(
    private val repository: StoreDetailsRepository
) {
    operator fun invoke(storeId: String): Flow<StoreDetailsResponse> {
        return repository.getStoreDetails(storeId)
    }
}
