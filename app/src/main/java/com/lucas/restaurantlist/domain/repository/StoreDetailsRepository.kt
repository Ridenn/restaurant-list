package com.lucas.restaurantlist.domain.repository

import com.lucas.restaurantlist.data.model.StoreDetailsResponse
import kotlinx.coroutines.flow.Flow

interface StoreDetailsRepository {
    fun getStoreDetails(id: String) : Flow<StoreDetailsResponse>
}