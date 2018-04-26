package io.varol.flixbus.data.remote.departures

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by varol on 26.4.2018.
 */

@Module
class RoutesRepoModule {
    @Provides
    @Singleton
    fun providesRoutesApiModule(departuresApi: DeparturesApi): DeparturesRepo {
        return DeparturesRepo(departuresApi)
    }

}