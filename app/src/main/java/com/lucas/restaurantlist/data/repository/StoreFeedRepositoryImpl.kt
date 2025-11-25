package com.lucas.restaurantlist.data.repository

import com.lucas.restaurantlist.data.RestaurantService
import com.lucas.restaurantlist.data.local.dao.StoreDao
import com.lucas.restaurantlist.data.local.model.toStoreEntity
import com.lucas.restaurantlist.data.local.model.toStoreResponse
import com.lucas.restaurantlist.data.model.StoreResponse
import com.lucas.restaurantlist.domain.repository.StoreFeedRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

class StoreFeedRepositoryImpl(
    private val remoteDataSource: RestaurantService,
    private val localDataSource: StoreDao
) : StoreFeedRepository {

    override fun getStoreFeed(latitude: Double, longitude: Double): Flow<List<StoreResponse>> {
        return localDataSource.getAllStores()
            .map { entities ->
                entities.map { it.toStoreResponse() }
            }
            .onStart {
                try {
                    val result = remoteDataSource.getStoreFeed(latitude, longitude)
                    localDataSource.insertAll(result.map { it.toStoreEntity() })
                } catch (e: Exception) {
                    throw e
                }
            }
            .flowOn(Dispatchers.IO)
    }
}
