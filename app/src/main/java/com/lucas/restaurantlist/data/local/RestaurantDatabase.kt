package com.lucas.restaurantlist.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lucas.restaurantlist.data.local.dao.StoreDao
import com.lucas.restaurantlist.data.local.model.StoreEntity

@Database(entities = [StoreEntity::class], version = 1, exportSchema = false)
abstract class RestaurantDatabase : RoomDatabase() {
    abstract fun storeDao(): StoreDao
}
