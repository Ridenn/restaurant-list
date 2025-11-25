package com.lucas.restaurantlist.domain.usecase

import com.lucas.restaurantlist.data.model.StoreResponse
import com.lucas.restaurantlist.domain.repository.StoreFeedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Use case for retrieving the store feed.
 *
 * This use case retrieves the list of stores and filters them to include only those that are "OPEN".
 * It also sorts the stores alphabetically by name.
 */
class GetStoreFeedUseCase(
    private val repository: StoreFeedRepository
) {
    operator fun invoke(latitude: Double, longitude: Double): Flow<List<StoreResponse>> {
        return repository.getStoreFeed(latitude, longitude)
            .map { stores ->
                stores.distinctBy { it.id }
                    .sortedBy { it.name }
                //.filter { it.status == "Opened" }
            }
    }
}
