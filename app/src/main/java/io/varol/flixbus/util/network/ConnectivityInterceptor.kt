package io.varol.flixbus.util.network

import android.content.Context
import io.varol.flixbus.util.common.AndroidUtils
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * Created by varol on 26.4.2018.
 */

/**
 * We will add this interceptor to OkHttp builder,
 * It will help us to catch if there is no internet connection
 * Also if there is no internet, Retrofit will not wait until timeout
 */
class ConnectivityInterceptor : Interceptor {

    private var mContext: Context

    constructor(context: Context) {
        mContext = context
    }


    override fun intercept(chain: Interceptor.Chain?): Response {
        if (!AndroidUtils.isNetworkConnected(mContext)) {
            throw NoInternetException(mContext)
        }

        val builder: Request.Builder = chain!!.request()!!.newBuilder()
        return chain.proceed(builder.build())
    }
}