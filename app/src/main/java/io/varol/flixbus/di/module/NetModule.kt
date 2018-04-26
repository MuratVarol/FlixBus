package io.varol.flixbus.di.module

import android.app.Application
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.Rfc3339DateJsonAdapter
import dagger.Module
import dagger.Provides
import io.varol.flixbus.BuildConfig
import io.varol.flixbus.util.network.ConnectivityInterceptor
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by varol on 26.4.2018.
 */

@Module
class NetModule {


    companion object {
        private const val CLIENT_TIME_OUT = 10L
        private const val CLIENT_CACHE_SIZE = 10 * 1024 * 1024L
        private const val CLIENT_CACHE_DIRECTORY = "http"

        private const val BASE_URL = "http://api.mobile.staging.mfb.io"

    }

    /**
     * Provides Cache object for OkHttp
     */
    @Provides
    @Singleton
    fun providesCache(application: Application): Cache = Cache(File(application.cacheDir,
            CLIENT_CACHE_DIRECTORY),
            CLIENT_CACHE_SIZE)

    /**
     * Provides Moshi for Json parsing
     */
    @Provides
    @Singleton
    fun providesMoshi(): Moshi {
        return Moshi.Builder()
                .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
                .build()
    }

    /**
     * Provides modified OkHttp Client builder.
     *  logging network requests and responses
     *  Checks if there is no internet connection
     *  Sets timeouts for requests and response wait
     */
    @Provides
    @Singleton
    fun providesOkHttpClient(cache: Cache, context: Application): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor()
                        .setLevel(if (BuildConfig.DEBUG) Level.BODY else Level.NONE))
                .addInterceptor(ConnectivityInterceptor(context.applicationContext))
                .connectTimeout(CLIENT_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(CLIENT_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(CLIENT_TIME_OUT, TimeUnit.SECONDS)
                .cache(cache)
                .build()
    }


    /**
     * Retrofit builder for Service communications
     */
    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit =
            Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(MoshiConverterFactory.create(providesMoshi()))
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .build()


}