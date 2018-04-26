package io.varol.flixbus.di.component

import android.app.Application
import android.content.res.Resources
import com.squareup.leakcanary.RefWatcher
import com.squareup.moshi.Moshi
import dagger.Component
import io.varol.flixbus.data.remote.departures.DeparturesRepoModule
import io.varol.flixbus.di.module.AppModule
import io.varol.flixbus.di.module.LeakCanaryModule
import io.varol.flixbus.di.module.NetModule
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by varol on 26.4.2018.
 */


@Singleton
@Component(modules = [
    (AppModule::class),
    (NetModule::class),
    (DeparturesRepoModule::class),
    (LeakCanaryModule::class)
])

interface AppComponent {

    fun application(): Application

    fun resources(): Resources

    fun cache(): Cache

    fun okHttpClient(): OkHttpClient

    fun moshi(): Moshi

    fun retrofit(): Retrofit

    fun departuresApi(): DeparturesRepoModule

    fun refWatcher(): RefWatcher
}
