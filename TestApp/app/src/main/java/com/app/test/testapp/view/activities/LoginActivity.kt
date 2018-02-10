package com.app.test.testapp.view.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.app.test.testapp.R
import com.app.test.testapp.data.local.TestEntity
import com.app.test.testapp.extensions.onClick
import com.app.test.testapp.view.vm.TestViewModel
import kotlinx.android.synthetic.main.activity_login.*

/**
 * Created by Enrique on 2/10/2018.
 */
class LoginActivity : BaseActivity() {

    private val testViewModel by lazy {
        ViewModelProviders.of(this@LoginActivity).get(TestViewModel::class.java)
    }

    private val list by lazy {
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
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        initLiveDataObservers()

        checkForAppPermissions(arrayOf(BaseActivity.ACCESS_FINE_LOCATION, BaseActivity.ACCESS_COARSE_LOCATION), perRequestCode, true)

    }

    private fun initLiveDataObservers() {
        testViewModel.liveDataSaved.observe(this, Observer { result ->

        })
        testViewModel.saveModels(list.toTypedArray())

    }

    override fun onResume() {
        super.onResume()
        iniViewClickListeners()
    }

    private fun iniViewClickListeners() {

        listOf<View>(loginBtn).onClick {
            when (it.id) {
                loginBtn.id -> {
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                }
            }
        }
    }
}