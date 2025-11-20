package com.lucas.restaurantlist

import android.app.Application
import com.lucas.restaurantlist.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RestaurantListApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@RestaurantListApplication)
            modules(listOf(mainModule))
        }
    }
}
