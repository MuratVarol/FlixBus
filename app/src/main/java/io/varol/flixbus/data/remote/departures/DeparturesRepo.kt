package io.varol.flixbus.data.remote.departures

import io.varol.flixbus.data.models.Departures
import io.varol.flixbus.data.models.ServiceResponse
import javax.inject.Inject

/**
 * Created by varol on 26.4.2018.
 */

class DeparturesRepo {

    private var mDeparturesApi: DeparturesApi

    @Inject
    constructor(departuresApi: DeparturesApi) {
        mDeparturesApi = departuresApi
    }

    suspend fun fetchDepartures(): ServiceResponse {
        return mDeparturesApi.fetchDeparturesTimeTable().await()
    }


}