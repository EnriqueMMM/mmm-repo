package com.app.test.testapp.data.local

import android.arch.persistence.room.*

/**
 * Created by Enrique on 2/10/2018.
 */

@Dao
abstract class LocalDAO {

    @Transaction
    open fun replaceAllInTransaction(statuses: Array<TestEntity>): Pair<Int, Array<Long>> {
        val d = removeAll()
        val i = addAll(statuses)
        return Pair(d, i)
    }

    @Query("SELECT * FROM ${LocalContract.TEST_ENTITY} where id = :id")
    abstract fun getById(id: Long): TestEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun addAll(statuses: Array<TestEntity>): Array<Long>

    @Query("SELECT * FROM ${LocalContract.TEST_ENTITY}")
    abstract fun getAll(): Array<TestEntity>

    @Query("DELETE FROM ${LocalContract.TEST_ENTITY}")
    abstract fun removeAll(): Int

    @Query("DELETE FROM ${LocalContract.TEST_ENTITY} where id = :id")
    abstract fun removeById(id: Long): Int
}