package io.varol.flixbus.ui

import io.varol.flixbus.app.base.BaseContract
import io.varol.flixbus.data.models.Departures

/**
 * Created by varol on 26.4.2018.
 */

interface DeparturesListContract {

    interface View : BaseContract.View {

        fun showDeparturesList(departuresList: List<Departures>)

        fun clearList()

        fun showError(errorMessage: String)

        fun showNoInternetSnack()

        fun showEmptyResult()

    }

    interface Presenter {

        fun loadDeparturesList(cleanPreviousList: Boolean)


    }

}