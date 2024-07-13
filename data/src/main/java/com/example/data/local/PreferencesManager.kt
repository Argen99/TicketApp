package com.example.data.local

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(context: Context) {

    private val sharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
    }

    var lastDepartureLocation: String?
        get() = getDataByKey(KEY_LAST_DEPARTURE_LOCATION)
        set(value) = setDataKey(KEY_LAST_DEPARTURE_LOCATION, value)


    private fun getDataByKey(key: String): String? =
        sharedPreferences.getString(key, null)

    private fun setDataKey(key: String, value: String?) {
        sharedPreferences
            .edit()
            .putString(key, value)
            .apply()
    }

    companion object {
        private const val KEY_LAST_DEPARTURE_LOCATION = "last_departure_location"
    }
}

