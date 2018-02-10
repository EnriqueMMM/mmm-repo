package com.app.test.testapp.view.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by Enrique on 1/23/2018.
 */
class MainPagerAdapter(fm: FragmentManager, frgList: List<Fragment>) : FragmentPagerAdapter(fm) {

    private val fragments: List<Fragment> = frgList

    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getCount(): Int = fragments.size


}