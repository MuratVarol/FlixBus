package io.varol.flixbus.util.network

import android.content.Context
import io.varol.flixbus.R
import java.io.IOException

/**
 * Created by varol on 26.4.2018.
 */

class NoInternetException(context: Context) : IOException() {

    private val mContext = context

    override val message: String?
        get() =  mContext.resources.getString(R.string.no_internet_connection)
}