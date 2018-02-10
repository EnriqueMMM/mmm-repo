package com.app.test.testapp.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

/**
 * Created by Enrique on 2/10/2018.
 */

@Database(version = 1, entities = [TestEntity::class])
abstract class LocalDataSource : RoomDatabase() {
    abstract fun localDao(): LocalDAO

    companion object {
        fun buildPersistentInstance(context: Context): LocalDataSource = Room.databaseBuilder(
                context.applicationContext,
                LocalDataSource::class.java,
                LocalContract.DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }
}