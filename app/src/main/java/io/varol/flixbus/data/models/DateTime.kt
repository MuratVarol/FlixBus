package io.varol.flixbus.data.models

import com.squareup.moshi.Json

/**
 * Created by varol on 26.4.2018.
 */
 
data class DateTime(
        @Json(name = "timestamp")
        var timestamp: Long,

        @Json(name = "tz")
        var tz: String)