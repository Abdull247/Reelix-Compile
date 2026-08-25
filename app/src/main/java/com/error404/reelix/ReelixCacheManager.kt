package com.error404.reelix

import android.content.Context
import android.content.SharedPreferences

class ReelixCacheManager private constructor(context: Context) {

    private val sharedPreferences: SharedPreferences = 
        context.applicationContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREF_NAME = "ReelixOfflineCache"
        
        @Volatile
        private var INSTANCE: ReelixCacheManager? = null

        @JvmStatic
        fun initialize(context: Context): ReelixCacheManager {
            return INSTANCE ?: synchronized(this) {
                val instance = ReelixCacheManager(context)
                INSTANCE = instance
                instance
            }
        }

        @JvmStatic
        fun getInstance(): ReelixCacheManager {
            return INSTANCE ?: throw IllegalStateException(
                "ReelixCacheManager is not initialized. Call initialize(context) in your Activity or Application class first."
            )
        }
    }

    /**
     * Saves the raw JSON string response directly into SharedPreferences
     */
    fun saveResponse(key: String, jsonResponse: String?) {
        if (!jsonResponse.isNullOrEmpty()) {
            sharedPreferences.edit().putString(key, jsonResponse).apply()
        }
    }

    /**
     * Retrieves the stored raw JSON string response. Returns an empty string if no cache exists.
     */
    fun getResponse(key: String): String {
        return sharedPreferences.getString(key, "") ?: ""
    }
    
    /**
     * Quick safety check to see if a specific cache route contains data
     */
    fun hasCache(key: String): Boolean {
        return !getResponse(key).isEmpty()
    }
}
