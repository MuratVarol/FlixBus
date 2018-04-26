package io.varol.flixbus.data.models

import com.squareup.moshi.Json

/**
 * Created by varol on 26.4.2018.
 */
 
data class DateTime(
        @Json(name = "timestamp")
        val timestamp: Long,

        @Json(name = "tz")
        val tz: String)