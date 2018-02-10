package com.app.test.testapp.view.fragments

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.test.testapp.R
import com.app.test.testapp.view.adapters.MainPagerAdapter
import kotlinx.android.synthetic.main.navi_frag_first_layout.*

/**
 * Created by Enrique on 2/10/2018.
 */
class NavigationFragmentFirst : BaseFragment() {

    val fragmentOne = TabFragmentOne.newInstance()
    val fragmentTwo = TabFragmentTwo.newInstance()
    val fragments = listOf(fragmentOne, fragmentTwo)


    companion object {
        fun newInstance(): NavigationFragmentFirst{
            return NavigationFragmentFirst()
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.navi_frag_first_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let { ac ->
            tabViewPager.adapter = MainPagerAdapter(ac.supportFragmentManager, fragments)
        }

    }

    override fun onResume() {
        super.onResume()

        tabViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
            }
        })
    }
}