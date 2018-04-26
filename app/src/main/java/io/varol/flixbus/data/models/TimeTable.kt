package io.varol.flixbus.data.models

import com.squareup.moshi.Json

/**
 * Created by varol on 26.4.2018.
 */
 
 data class TimeTable(@Json(name = "departures") val departures: ArrayList<Departures>)