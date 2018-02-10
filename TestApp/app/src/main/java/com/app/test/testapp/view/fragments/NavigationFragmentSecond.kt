package com.app.test.testapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.test.testapp.R
import com.app.test.testapp.extensions.onClick
import com.urbamap.urbamap.utils.SharedPreferenceKeys
import com.urbamap.urbamap.utils.SharedPreferenceManager
import kotlinx.android.synthetic.main.navi_frag_second_layout.*

/**
 * Created by Enrique on 2/10/2018.
 */
class NavigationFragmentSecond : BaseFragment() {

    var isChecked = false

    companion object {
        fun newInstance(): NavigationFragmentSecond {
            return NavigationFragmentSecond()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.navi_frag_second_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewCLickListeners()
        isChecked = SharedPreferenceManager[SharedPreferenceKeys.GENERATE_NOTIFI, false]
        notiBtn.text = if (isChecked) "Pressed" else "Press"
    }

    private fun initViewCLickListeners() {
        listOf<View>(notiBtn).onClick {
            when (it.id) {
                notiBtn.id -> {
                    isChecked = !isChecked
                    SharedPreferenceManager[SharedPreferenceKeys.GENERATE_NOTIFI] = isChecked
                    notiBtn.text = if (isChecked) "Pressed" else "Press"
                }
            }
        }

    }
}