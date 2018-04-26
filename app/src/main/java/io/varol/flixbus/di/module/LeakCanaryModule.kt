package io.varol.flixbus.di.module

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by varol on 26.4.2018.
 */

@Module
class LeakCanaryModule {

    @Provides
    @Singleton
    fun providesRefWatcher(application: Application): RefWatcher = LeakCanary.install(application)
}