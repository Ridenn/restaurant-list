package com.lucas.restaurantlist.data.model

import com.google.gson.annotations.SerializedName

/**
 * Store address remote data.
 */
data class StoreAddressResponse(
    @SerializedName("printable_address")
    val printableAddress: String
)
