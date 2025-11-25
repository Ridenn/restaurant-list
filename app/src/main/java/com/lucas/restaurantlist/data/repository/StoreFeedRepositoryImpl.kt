package com.lucas.restaurantlist.data.repository

import android.util.Log
import com.lucas.restaurantlist.data.RestaurantService
import com.lucas.restaurantlist.data.local.dao.StoreDao
import com.lucas.restaurantlist.data.local.model.toStoreEntity
import com.lucas.restaurantlist.data.local.model.toStoreResponse
import com.lucas.restaurantlist.data.model.StoreResponse
import com.lucas.restaurantlist.domain.repository.StoreFeedRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class StoreFeedRepositoryImpl(
    private val remoteDataSource: RestaurantService,
    private val localDataSource: StoreDao
) : StoreFeedRepository {

    override fun getStoreFeed(latitude: Double, longitude: Double): Flow<List<StoreResponse>> = channelFlow {
        // Launch network fetch asynchronously
        launch {
            try {
                val result = remoteDataSource.getStoreFeed(latitude, longitude)
                localDataSource.insertAll(result.map { it.toStoreEntity() })
            } catch (e: Exception) {
                // If network fails, we just log it and let the local data flow continue.
                // This ensures offline-first behavior (showing cached data).
                Log.e("StoreFeedRepository", "Network error: $e")
            }
        }

        // Observe local data immediately
        localDataSource.getAllStores().collect { entities ->
            send(entities.map { it.toStoreResponse() })
        }
    }.flowOn(Dispatchers.IO)
}
