package com.app.test.testapp.view.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.test.testapp.R

/**
 * Created by Enrique on 1/23/2018.
 */
open class TabFragmentTwo : BaseFragment() {


    companion object {
        fun newInstance(): TabFragmentTwo {
            return TabFragmentTwo()
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.tab_two_layout, container, false)


}