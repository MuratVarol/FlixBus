package io.varol.flixbus.util.network

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * Created by varol on 26.4.2018.
 */

class HeaderInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder().addHeader(HEADER_KEY, HEADER_VALUE).build()
        return chain.proceed(request)
    }

    companion object {
        private val HEADER_KEY = "X-Api-Authentication"
        private val HEADER_VALUE = "intervIEW_TOK3n"
    }
}