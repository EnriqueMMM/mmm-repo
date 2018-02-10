package com.app.test.testapp.view.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.test.testapp.R
import com.app.test.testapp.view.activities.ItemActivity
import com.app.test.testapp.view.adapters.TestModelAdapter
import com.app.test.testapp.view.vm.TestViewModel
import kotlinx.android.synthetic.main.tab_one_layout.*

/**
 * Created by Enrique on 1/23/2018.
 */
open class TabFragmentOne : BaseFragment() {

    private val testViewModel by lazy {
        ViewModelProviders.of(this@TabFragmentOne).get(TestViewModel::class.java)
    }

    val testAdapter by lazy { TestModelAdapter() }

    companion object {
        fun newInstance(): TabFragmentOne {
            return TabFragmentOne()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.tab_one_layout, container, false)

        initLiveDataObservers()
        return v
    }

    private fun initLiveDataObservers() {
        testViewModel.liveDataList.observe(this, Observer { result ->
            testAdapter.setData(result?.first?.toList() ?: listOf())
        })

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        recyclerList.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = testAdapter
        }

        initViewClickListeners()
    }

    private fun initViewClickListeners() {


    }


    override fun onResume() {
        super.onResume()
        testAdapter.setOnItemSelected { model ->
            val i = Intent(activity, ItemActivity::class.java)
            i.putExtra("id", model.id)
            startActivity(i)
        }
        testViewModel.getModels()
    }


}