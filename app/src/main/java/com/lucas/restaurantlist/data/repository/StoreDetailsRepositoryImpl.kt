package com.lucas.restaurantlist.data.repository

import com.lucas.restaurantlist.data.RestaurantService
import com.lucas.restaurantlist.data.model.StoreDetailsResponse
import com.lucas.restaurantlist.domain.repository.StoreDetailsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class StoreDetailsRepositoryImpl(
    private val remoteDataSource: RestaurantService
) : StoreDetailsRepository {

    override fun getStoreDetails(id: String): Flow<StoreDetailsResponse> = flow {
        val result = remoteDataSource.getStoreDetails(id)
        emit(result)
    }.flowOn(Dispatchers.IO)
}