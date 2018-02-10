package com.app.test.testapp.view.activities

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.view.ViewPager
import com.app.test.testapp.R
import com.app.test.testapp.view.adapters.MainPagerAdapter
import com.app.test.testapp.view.fragments.NavigationFragmentFirst
import com.app.test.testapp.view.fragments.NavigationFragmentSecond
import com.app.test.testapp.view.fragments.NavigationFragmentTheird
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    val naviFragFirst = NavigationFragmentFirst.newInstance()
    val naviFragSecond = NavigationFragmentSecond.newInstance()
    val naviFragTheird = NavigationFragmentTheird.newInstance()
    val fragments = listOf(naviFragFirst, naviFragSecond, naviFragTheird)

    val navigationListener by lazy {
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigationFirst -> {

                    viewPager.currentItem = 0
                    return@OnNavigationItemSelectedListener true

                }
                R.id.navigationSecond -> {
                    viewPager.currentItem = 1
                    return@OnNavigationItemSelectedListener true

                }
                R.id.navigationTheird -> {
                    viewPager.currentItem = 2
                    naviFragTheird.initLocationListeners()
                    return@OnNavigationItemSelectedListener true
                }
                else -> false
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager.adapter = MainPagerAdapter(supportFragmentManager, fragments)
        viewPager.offscreenPageLimit = 2
    }

    override fun onResume() {
        super.onResume()

        navigation.setOnNavigationItemSelectedListener(navigationListener)
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
            }
        })
    }
}
