package com.app.test.testapp.extensions

import ru.gildor.coroutines.retrofit.ErrorResult
import ru.gildor.coroutines.retrofit.Result
import ru.gildor.coroutines.retrofit.getOrNull

/**
 * Created by Enrique on 12/12/2017.
 */
inline fun <T : Any> Result<T>.ifFailed(handler: () -> Unit): Result<T> {
    if (this is ErrorResult) handler()
    return this
}

inline fun <T : Any> Result<T>.ifSucceeded(handler: (data: T) -> Unit): Result<T> {
    (this as? Result.Ok)?.getOrNull()?.let { handler(it) }
    return this
}

inline fun <T : Any> Result<T>.ifError(handler: (code: Int) -> Unit): Result<T> {
    (this as? Result.Error)?.response?.code()?.let { handler(it) }
    return this
}

inline fun <T : Any> Result<T>.ifException(handler: (exception: Throwable) -> Unit): Result<T> {
    (this as? Result.Exception)?.exception?.let { handler(it) }
    return this
}
