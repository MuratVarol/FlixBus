package io.varol.flixbus.app.base

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.AppCompatDelegate
import io.varol.flixbus.app.App
import io.varol.flixbus.di.component.AppComponent

/**
 * Created by varol on 26.4.2018.
 */

abstract class BaseActivity : AppCompatActivity(), BaseContract.View
{
    private val TAG = "BaseActivity"

    private var mPresenter: BasePresenter<*>? = null


    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)

        injectComponents()

    }


    protected abstract fun injectComponents()


    fun getAppComponent(): AppComponent = App.appComponent

    override fun initPresenter(presenter: BasePresenter<*>) {
        this.mPresenter = presenter
    }


    override fun onDestroy() {
        super.onDestroy()

        mPresenter?.detachView()
        mPresenter = null

//    refWatcher.watch(this, TAG)
    }
}