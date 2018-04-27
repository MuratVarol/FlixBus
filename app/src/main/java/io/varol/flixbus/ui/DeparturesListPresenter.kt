package io.varol.flixbus.ui

import io.varol.flixbus.app.base.BasePresenter
import io.varol.flixbus.data.remote.departures.DeparturesRepo
import io.varol.flixbus.util.network.NoInternetException
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import retrofit2.HttpException
import javax.inject.Inject

/**
 * Created by varol on 26.4.2018.
 */

class DeparturesListPresenter : BasePresenter<DeparturesListContract.View>, DeparturesListContract.Presenter {

    private var mDeparturesRepo: DeparturesRepo

    @Inject
    constructor(departuresRepo: DeparturesRepo) {
        mDeparturesRepo = departuresRepo
    }


    /**
     * This method will call service and after get valid data;
     * will call methods for put data on screen
     */
    override fun loadDeparturesList() {

        launch(UI) {
            try {

                view?.showLoadingDialog()

                val fetchedDepartures = mDeparturesRepo.fetchDepartures()

                view?.cleanDeparturesList()

                view?.showDeparturesList(fetchedDepartures.timetable.departures)

            } catch (nie: NoInternetException) {
                view?.showNoInternetSnack()

            } catch (he: HttpException) {

                view?.showError(he.message())

            } catch (e: Exception) {
                /**
                 * todo:
                 * It is not correct approach that show user exception message
                 * I will back here if I have time
                 */
                view?.showError(e.message ?: "Error")

            } finally {
                view?.hideLoadingDialog()
            }

        }


    }


}