package io.varol.flixbus.data.models

import com.squareup.moshi.Json

/**
 * Created by varol on 26.4.2018.
 */
 
 data class Departures(
        
        @Json(name = "datetime")
        var datetime: DateTime,

        @Json(name = "line_code")
        var line_code: String,

        @Json(name = "direction")
        var direction: String)