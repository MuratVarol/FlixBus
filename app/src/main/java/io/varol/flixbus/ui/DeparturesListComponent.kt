package io.varol.flixbus.ui

import dagger.Component
import io.varol.flixbus.app.scope.PerActivity
import io.varol.flixbus.di.component.AppComponent

/**
 * Created by varol on 26.4.2018.
 */

@PerActivity
@Component(dependencies = [(AppComponent::class)], modules = [(DeparturesListModule::class)])
interface DeparturesListComponent {
    fun inject(departuresListActivity: DeparturesListActivity)
}
