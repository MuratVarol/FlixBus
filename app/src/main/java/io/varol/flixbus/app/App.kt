package io.varol.flixbus.app

import android.support.multidex.MultiDexApplication
import io.varol.flixbus.di.component.AppComponent
import io.varol.flixbus.di.component.DaggerAppComponent
import io.varol.flixbus.di.module.AppModule
import io.varol.flixbus.di.module.NetModule

/**
 * Created by varol on 26.4.2018.
 */


open class App : MultiDexApplication()
{

    companion object{
        @JvmStatic lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        initDagger()

    }

    /**
     * Initialize dagger 2 for injection
     */
    private fun initDagger() {
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .netModule(NetModule())
                .build()
    }


}