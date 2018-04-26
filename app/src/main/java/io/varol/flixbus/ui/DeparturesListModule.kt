package io.varol.flixbus.ui

import dagger.Module
import dagger.Provides
import io.varol.flixbus.app.scope.PerActivity
import io.varol.flixbus.data.remote.departures.DeparturesRepo

/**
 * Created by varol on 26.4.2018.
 */

@Module
class DeparturesListModule {

    @PerActivity
    @Provides
    internal fun providesDeparturesListModule(departuresRepo: DeparturesRepo): DeparturesListPresenter = DeparturesListPresenter(departuresRepo)
}