package com.lucas.restaurantlist.domain.usecase

import com.lucas.restaurantlist.data.model.StoreResponse
import com.lucas.restaurantlist.domain.repository.StoreFeedRepository

/**
 * Use case for retrieving the store feed.
 *
 * This use case retrieves the list of stores and filters them to include only those that are "OPEN".
 * It also sorts the stores alphabetically by name.
 */
class GetStoreFeedUseCase(
    private val repository: StoreFeedRepository
) {
    suspend operator fun invoke(latitude: Double, longitude: Double): List<StoreResponse> {
        val stores = repository.getStoreFeed(latitude, longitude)
        return stores.filter { it.status == "Opened" }
            .sortedBy { it.name }
    }
}
