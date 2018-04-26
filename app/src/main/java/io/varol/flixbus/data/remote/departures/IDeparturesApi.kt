package io.varol.flixbus.data.remote.departures

import io.varol.flixbus.data.models.TimeTable
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET

/**
 * Created by varol on 26.4.2018.
 */

interface IDeparturesApi {

    @GET("/mobile/v1/network/station/10/timetable")
    fun fetchDeparturesTimeTable(): Deferred<TimeTable>
}

