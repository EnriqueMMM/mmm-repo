package com.app.test.testapp.extensions

/**
 * Created by Enrique on 12/5/2017.
 */
fun Array<String>.fromArrayToStringLiteral(): String{

    return this.joinToString(separator = ",", prefix = "[", postfix = "]", transform = { s: String -> "\"".plus(s).plus("\"")})
}