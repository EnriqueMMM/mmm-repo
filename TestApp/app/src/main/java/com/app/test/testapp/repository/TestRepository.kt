package com.app.test.testapp.repository

import com.app.test.testapp.data.local.LocalDataSource
import com.app.test.testapp.data.local.TestEntity

/**
 * Created by Enrique on 2/10/2018.
 */
class TestRepository(private val localDataSource: LocalDataSource) {

    fun getModels(): Array<TestEntity> {
        return localDataSource.localDao().getAll()
    }

    fun saveModels(categories: Array<TestEntity>): Boolean {
        val r = localDataSource.localDao().replaceAllInTransaction(categories)
        return r.first > 0 && r.second.isNotEmpty()
    }

    fun getModelByIdLocal(id: Long): TestEntity {
        return localDataSource.localDao().getById(id)
    }

    fun removeModelByIdLocal(id: Long): Boolean {
        val r = localDataSource.localDao().removeById(id)
        return r > 0
    }
}