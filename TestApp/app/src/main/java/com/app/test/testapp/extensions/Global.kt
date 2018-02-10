package com.app.test.testapp.extensions

import org.koin.KoinContext
import org.koin.standalone.StandAloneContext

/**
 * Created by Enrique on 12/6/2017.
 */
inline fun <reified T> inject(name: String = "") = lazy {
    (StandAloneContext.koinContext as KoinContext).get<T>(name)
}