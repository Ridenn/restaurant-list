package com.lucas.restaurantlist.features.storefeed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucas.restaurantlist.Constants.DEFAULT_LATITUDE
import com.lucas.restaurantlist.Constants.DEFAULT_LONGITUDE
import com.lucas.restaurantlist.data.model.StoreResponse
import com.lucas.restaurantlist.domain.usecase.GetStoreFeedUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class StoreFeedViewModel(
    private val getStoreFeedUseCase: GetStoreFeedUseCase
) : ViewModel() {

    init {
        getStoreFeed()
    }

    sealed class StoreFeedState {
        data class BindData(val storeFeed: List<StoreResponse>): StoreFeedState()
        object Error: StoreFeedState()
        object Loading: StoreFeedState()
    }

    private val _getStoreFeedState by lazy { MutableStateFlow<StoreFeedState>(StoreFeedState.Loading) }
    val getStoreFeedState = _getStoreFeedState

    fun getStoreFeed() = viewModelScope.launch {
        _getStoreFeedState.value = StoreFeedState.Loading

        try {
            val response = getStoreFeedUseCase(DEFAULT_LATITUDE, DEFAULT_LONGITUDE)
            _getStoreFeedState.value = StoreFeedState.BindData(response)
        } catch (e: Exception) {
            _getStoreFeedState.value = StoreFeedState.Error
        }
    }
}
