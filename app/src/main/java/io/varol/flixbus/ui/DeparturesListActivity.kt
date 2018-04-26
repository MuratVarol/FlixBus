package io.varol.flixbus.ui

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import com.squareup.leakcanary.RefWatcher
import io.varol.flixbus.R
import io.varol.flixbus.app.base.BaseActivity
import io.varol.flixbus.data.models.Departures
import kotlinx.android.synthetic.main.activity_departures_list.*
import javax.inject.Inject

class DeparturesListActivity : BaseActivity(), DeparturesListContract.View {

    private val TAG = "DeparturesListActivity"

    @Inject
    lateinit var presenter: DeparturesListPresenter
    @Inject
    lateinit var refWatcher: RefWatcher


    private lateinit var departuresAdapter: CommonAdapter<Departures>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_departures_list)

        injectComponents()

        presenter.attachView(this)

        initView()

        initVariables()

    }

    override fun injectComponents() {

        DaggerDeparturesListComponent.builder()
                .appComponent(getAppComponent())
                .departuresListModule(DeparturesListModule())
                .build()
                .inject(this)
    }


    override fun initView() {

        departuresAdapter = CommonAdapter()

        rv_departures_items?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_departures_items?.adapter = departuresAdapter

        departuresAdapter.setOnDepartureItemClickListener(object : CommonAdapter.OnDepartureItemClickListener {
            override fun onItemClick(position: Int) {
                /**
                 * NOTHING TO DO
                 */
            }

        })

    }

    override fun initVariables() {

        presenter.loadDeparturesList()

    }

    override fun showLoadingDialog() {
        pb_departures_loader?.visibility = VISIBLE
    }

    override fun hideLoadingDialog() {
        pb_departures_loader?.visibility = GONE
    }

    override fun showDeparturesList(departuresList: List<Departures>?) {

        if (departuresList != null && departuresList.isNotEmpty()) {
            departuresAdapter.addAll(departuresList)
        }
    }

    override fun cleanDeparturesList() {
        departuresAdapter.clear()
    }

    override fun showError(errorMessage: String) {
        Toast.makeText(this,errorMessage,Toast.LENGTH_SHORT)
    }

    override fun showNoInternetSnack() {
        Snackbar.make(rl_departures_layout,R.string.no_internet_connection,Snackbar.LENGTH_SHORT)
    }

    override fun showEmptyResult() {

    }


    override fun onDestroy() {
        super.onDestroy()
        refWatcher.watch(this, TAG)
    }
}
