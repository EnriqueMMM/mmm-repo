package com.urbamap.urbamap.utils

import android.content.SharedPreferences
import com.app.test.testapp.extensions.inject

object SharedPreferenceManager {

    private val sharedP by inject<SharedPreferences>()

//    private val sharedP by lazy { PreferenceManager.getDefaultSharedPreferences(a.applicationContext) }

    operator fun <T> get(key: String, defValue: T): T {
        return try {
//            val sharedP = PreferenceManager.getDefaultSharedPreferences(MainApp.instance)
            sharedP.all[key] as T ?: defValue
        } catch (ex: Exception) {
            defValue
        }
    }

    operator fun set(key: String, value: Any) {
        val editor = sharedP.edit()
        when (value) {
            is Boolean -> editor.putBoolean(key, value).commit()
            is Int -> editor.putInt(key, value).commit()
            is String -> editor.putString(key, value).commit()
            is Long -> editor.putLong(key, value).commit()
            is Float -> editor.putFloat(key, value).commit()
            is Set<*> ->
                try {
                    editor.putStringSet(key, value as MutableSet<String>?).apply()
                } catch (e: Exception) {
                    return
                }
        }
    }
}

