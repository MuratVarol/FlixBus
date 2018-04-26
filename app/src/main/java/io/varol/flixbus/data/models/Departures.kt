package io.varol.flixbus.data.models

import com.squareup.moshi.Json

/**
 * Created by varol on 26.4.2018.
 */
 
 data class Departures(
        
        @Json(name = "datetime")
        val datetime: DateTime,

        @Json(name = "line_code")
        val line_code: String,

        @Json(name = "direction")
        val direction: String)