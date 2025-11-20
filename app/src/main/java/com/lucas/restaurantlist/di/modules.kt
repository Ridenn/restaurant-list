package com.lucas.restaurantlist.di


import com.lucas.restaurantlist.data.RestaurantServiceFactory
import com.lucas.restaurantlist.data.repository.LoginRepositoryImpl
import com.lucas.restaurantlist.data.repository.StoreFeedRepositoryImpl
import com.lucas.restaurantlist.domain.repository.LoginRepository
import com.lucas.restaurantlist.domain.repository.StoreFeedRepository
import com.lucas.restaurantlist.features.login.LoginViewModel
import com.lucas.restaurantlist.features.login.SessionManagerPreferences
import com.lucas.restaurantlist.features.storefeed.StoreFeedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    single<SessionManagerPreferences> { SessionManagerPreferences(get()) }

    single<LoginRepository> { LoginRepositoryImpl(get(), get()) }
    single<StoreFeedRepository> { StoreFeedRepositoryImpl(get()) }

    factory { RestaurantServiceFactory.makeRestaurantService() }

    viewModel { LoginViewModel(get()) }
    viewModel { StoreFeedViewModel(get()) }
}