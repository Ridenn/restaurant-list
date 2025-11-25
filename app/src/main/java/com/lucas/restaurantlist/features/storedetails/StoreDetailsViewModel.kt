package com.lucas.restaurantlist.features.storedetails

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucas.restaurantlist.data.model.StoreAddressResponse
import com.lucas.restaurantlist.data.model.StoreDetailsResponse
import com.lucas.restaurantlist.data.model.StoreResponse
import com.lucas.restaurantlist.domain.usecase.GetStoreDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StoreDetailsViewModel(
    private val storeDetailsUseCase: GetStoreDetailsUseCase
) : ViewModel() {

    sealed class StoreDetailsState {
        data class BindData(val storeDetails: StoreDetailsResponse): StoreDetailsState()
        data class Error(val storeDetails: StoreDetailsResponse): StoreDetailsState()
        data object Loading: StoreDetailsState()
    }

    private val _getStoreDetailsState by lazy { MutableStateFlow<StoreDetailsState>(StoreDetailsState.Loading) }
    val getStoreDetailsState: StateFlow<StoreDetailsState> = _getStoreDetailsState

    fun getStoreDetails(storeId: String, fallbackStore: StoreResponse) = viewModelScope.launch {
        _getStoreDetailsState.value = StoreDetailsState.Loading
        try {
            storeDetailsUseCase.invoke(storeId).collect { response ->
                _getStoreDetailsState.value = StoreDetailsState.BindData(response)
            }
        } catch (e: Exception) {
            Log.e("Error", "Error while store details: $e")

            val fallbackDetails = StoreDetailsResponse(
                id = fallbackStore.id,
                name = fallbackStore.name,
                description = fallbackStore.description,
                coverImgUrl = fallbackStore.coverImgUrl,
                phoneNumber = "",
                deliveryEta = "",
                status = fallbackStore.status,
                deliveryFeeCents = fallbackStore.deliveryFeeCents.toIntOrNull() ?: 0,
                tags = emptyList(),
                address = StoreAddressResponse("")
            )

            _getStoreDetailsState.value = StoreDetailsState.Error(fallbackDetails)
        }
    }
}