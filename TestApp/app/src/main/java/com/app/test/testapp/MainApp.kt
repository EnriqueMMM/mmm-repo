package com.app.test.testapp

import android.app.Application
import com.app.test.testapp.data.local.TestEntity
import org.koin.android.ext.android.startKoin

/**
 * Created by Enrique on 2/9/2018.
 */
class MainApp : Application() {


    val initialList: MutableList<TestEntity> = mutableListOf()
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(AppModule()))

        initialList.addAll(
                listOf(
                        TestEntity(1, "name1", 1),
                        TestEntity(2, "name2", 2),
                        TestEntity(3, "name3", 3),
                        TestEntity(4, "name4", 4),
                        TestEntity(5, "name5", 5),
                        TestEntity(6, "name6", 6),
                        TestEntity(7, "name7", 7),
                        TestEntity(8, "name8", 8),
                        TestEntity(9, "name9", 9),
                        TestEntity(10, "name10", 10)
                )
        )


    }

}