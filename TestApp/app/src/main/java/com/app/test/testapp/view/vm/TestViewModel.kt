package com.app.test.testapp.view.vm

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.app.test.testapp.data.local.TestEntity
import com.app.test.testapp.repository.TestRepository
import kotlinx.coroutines.experimental.async
import org.koin.android.ext.android.inject

/**
 * Created by Enrique on 2/10/2018.
 */
class TestViewModel(app: Application) : AndroidViewModel(app) {

    val testRepository by app.inject<TestRepository>()

    val liveDataList: MutableLiveData<Triple<Array<TestEntity>, String, Int>> = MutableLiveData()
    val liveDataSaved: MutableLiveData<Triple<Boolean, String, Int>> = MutableLiveData()
    val liveDataSingle: MutableLiveData<Triple<TestEntity, String, Int>> = MutableLiveData()
    val liveDataRemove: MutableLiveData<Triple<Boolean, String, Int>> = MutableLiveData()

    fun saveModels(list: Array<TestEntity>) = async {
        val result = testRepository.saveModels(list)
        liveDataSaved.postValue(Triple(result, "", 0))
    }

    fun getModels() = async {
        val result = testRepository.getModels()
        liveDataList.postValue(Triple(result, "", 0))
    }

    fun getById(id: Long) = async {
        val result = testRepository.getModelByIdLocal(id)
        liveDataSingle.postValue(Triple(result, "", 0))
    }

    fun removeById(id: Long) = async {
        val result = testRepository.removeModelByIdLocal(id)
        liveDataRemove.postValue(Triple(result, "", 0))
    }
}