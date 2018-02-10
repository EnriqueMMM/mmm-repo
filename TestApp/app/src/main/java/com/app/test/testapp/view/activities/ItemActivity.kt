package com.app.test.testapp.view.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.app.test.testapp.R
import com.app.test.testapp.data.local.TestEntity
import com.app.test.testapp.extensions.onClick
import com.app.test.testapp.view.vm.TestViewModel
import kotlinx.android.synthetic.main.activity_item.*

/**
 * Created by Enrique on 2/10/2018.
 */
class ItemActivity : BaseActivity() {

    private val testViewModel by lazy {
        ViewModelProviders.of(this@ItemActivity).get(TestViewModel::class.java)
    }

    var id: Long = -1
    var model: TestEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_item)

        id = intent.extras.getLong("id")
        initLiveDataObservers()
    }

    override fun onResume() {
        super.onResume()
        testViewModel.getById(id)
        initViewClickListeners()
    }

    private fun initLiveDataObservers() {
        testViewModel.liveDataSingle.observe(this, Observer { result ->
            model = result?.first
            setViewData()
        })

        testViewModel.liveDataRemove.observe(this, Observer { result ->
            finish()
        })
    }

    private fun initViewClickListeners() {
        listOf<View>(removeFab).onClick {
            when (it.id) {
                removeFab.id -> {
                    testViewModel.removeById(id)
                }
            }
        }
    }

    private fun setViewData() {
        model?.let { m ->
            itemName.text = m.name
            itemPoints.text = m.points.toString()
        }

    }
}