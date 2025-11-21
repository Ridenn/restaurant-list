package com.lucas.restaurantlist.domain.usecase

import com.lucas.restaurantlist.data.model.StoreResponse
import com.lucas.restaurantlist.domain.repository.StoreFeedRepository

class GetStoreFeedUseCase(private val repository: StoreFeedRepository) {
    suspend operator fun invoke(latitude: Double, longitude: Double): List<StoreResponse> {
        return repository.getStoreFeed(latitude, longitude)
    }
}
