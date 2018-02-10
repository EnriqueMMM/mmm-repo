package com.app.test.testapp.extensions

import android.support.v7.widget.AppCompatImageView
import android.view.View

/**
 * Created by Enrique on 12/5/2017.
 */
fun List<View>.onClick(onClick: (View) -> Unit) {
    forEach { it.setOnClickListener(onClick) }
}

fun List<View>.onLongClick(listener: View.OnLongClickListener) {
    forEach {
        it.setOnLongClickListener(listener)
    }
}