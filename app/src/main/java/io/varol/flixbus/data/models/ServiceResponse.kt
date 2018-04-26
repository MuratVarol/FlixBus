package io.varol.flixbus.data.models

import com.squareup.moshi.Json

/**
 * Created by varol on 26.4.2018.
 */

data class ServiceResponse(@Json(name = "timetable") var timetable: TimeTable)