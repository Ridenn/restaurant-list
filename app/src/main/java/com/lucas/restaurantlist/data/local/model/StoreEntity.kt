package com.lucas.restaurantlist.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lucas.restaurantlist.data.model.StoreResponse

@Entity(tableName = "stores")
data class StoreEntity(
    @PrimaryKey val id: String,
    val name: String,
    val description: String,
    val coverImgUrl: String,
    val status: String,
    val deliveryFeeCents: String
)

fun StoreEntity.toStoreResponse() = StoreResponse(
    id = id,
    name = name,
    description = description,
    coverImgUrl = coverImgUrl,
    status = status,
    deliveryFeeCents = deliveryFeeCents
)

fun StoreResponse.toStoreEntity() = StoreEntity(
    id = id,
    name = name,
    description = description,
    coverImgUrl = coverImgUrl,
    status = status,
    deliveryFeeCents = deliveryFeeCents
)
