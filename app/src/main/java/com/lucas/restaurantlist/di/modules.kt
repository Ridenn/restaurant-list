package com.lucas.restaurantlist.di

import com.lucas.restaurantlist.data.RestaurantServiceFactory
import com.lucas.restaurantlist.data.repository.LoginRepositoryImpl
import com.lucas.restaurantlist.data.repository.StoreDetailsRepositoryImpl
import com.lucas.restaurantlist.data.repository.StoreFeedRepositoryImpl
import com.lucas.restaurantlist.domain.repository.LoginRepository
import com.lucas.restaurantlist.domain.repository.StoreDetailsRepository
import com.lucas.restaurantlist.domain.repository.StoreFeedRepository
import com.lucas.restaurantlist.domain.usecase.GetStoreDetailsUseCase
import com.lucas.restaurantlist.domain.usecase.GetStoreFeedUseCase
import com.lucas.restaurantlist.domain.usecase.RequestLoginUseCase
import com.lucas.restaurantlist.features.login.LoginViewModel
import com.lucas.restaurantlist.features.login.SessionManagerPreferences
import com.lucas.restaurantlist.features.storedetails.StoreDetailsViewModel
import com.lucas.restaurantlist.features.storefeed.StoreFeedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    single<SessionManagerPreferences> { SessionManagerPreferences(get()) }

    single<LoginRepository> { LoginRepositoryImpl(get(), get()) }
    single<StoreFeedRepository> { StoreFeedRepositoryImpl(get()) }
    single<StoreDetailsRepository> { StoreDetailsRepositoryImpl(get()) }

    factory { RestaurantServiceFactory.makeRestaurantService() }

    factory { RequestLoginUseCase(get()) }
    factory { GetStoreFeedUseCase(get()) }
    factory { GetStoreDetailsUseCase(get()) }

    viewModel { LoginViewModel(get()) }
    viewModel { StoreFeedViewModel(get()) }
    viewModel { StoreDetailsViewModel(get()) }
}