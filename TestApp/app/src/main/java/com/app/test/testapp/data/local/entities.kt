package com.app.test.testapp.data.local

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Enrique on 2/10/2018.
 */
@Entity(tableName = LocalContract.TEST_ENTITY)
data class TestEntity(
        @PrimaryKey(autoGenerate = true)
        val id: Long = -1,
        val name: String,
        val points: Int
)