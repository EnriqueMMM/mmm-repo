package com.app.test.testapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.test.testapp.R
import com.app.test.testapp.services.LocationService
import kotlinx.android.synthetic.main.navi_frag_theird_layout.*

/**
 * Created by Enrique on 2/10/2018.
 */
class NavigationFragmentTheird : BaseFragment() {

    companion object {
        fun newInstance(): NavigationFragmentTheird {
            return NavigationFragmentTheird()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.navi_frag_theird_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    fun initLocationListeners() {
        LocationService.setOnLocationListener { location ->
            latValue.text = location.latitude.toString()
            lonValue.text = location.longitude.toString()
        }
    }

    override fun onResume() {
        super.onResume()
    }
}