package io.varol.flixbus.util.common

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.support.annotation.Nullable

/**
 * Created by varol on 26.4.2018.
 */
class AndroidUtils{


    companion object {

        /**
         * @return ConnectivityManager
         */
        private fun getConnectivityManager(context: Context): ConnectivityManager? {
            return context.applicationContext
                    .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        }

        /**
         * @return {ConnectivityManager}'s active network information
         */
        @Nullable
        private fun getActiveNetworkInfo(context: Context): NetworkInfo? {
            val connectivityManager = getConnectivityManager(context) ?: return null
            return connectivityManager.activeNetworkInfo
        }

        /**
         * Whether there is an active network connection.
         *
         * @return boolean
         */
        fun isNetworkConnected(context: Context): Boolean {
            val activeNetworkInfo = getActiveNetworkInfo(context)
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }

    }
}
