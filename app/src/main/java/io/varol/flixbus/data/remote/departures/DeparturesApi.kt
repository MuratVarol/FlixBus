package io.varol.flixbus.data.remote.departures

import io.varol.flixbus.data.models.TimeTable
import kotlinx.coroutines.experimental.Deferred
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * Created by varol on 26.4.2018.
 */

class DeparturesApi : IDeparturesApi {

    //region variable declaration

    private var mRetrofit: Retrofit
    private var mDeparturesService: IDeparturesApi

    //endregion


    //region constructer

    @Inject
    constructor(retrofit: Retrofit) {
        mRetrofit = retrofit
        mDeparturesService = mRetrofit.create(IDeparturesApi::class.java)

    }

    //endregion


    //region Call methods

    override fun fetchDeparturesTimeTable(): Deferred<TimeTable> {
        return mDeparturesService.fetchDeparturesTimeTable()
    }


    //endregion

}