package com.zavaitar.core.network

import android.net.ConnectivityManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Module
class NetworkModule(private val networkState: NetworkState) {

    @Provides
    @Singleton
    fun networkInfo(): NetworkState = networkState

    @Provides
    @Singleton
    fun gson(): Gson = GsonBuilder()
        .create()

    @Provides
    fun retrofitBuilder(): Retrofit.Builder = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

    @Provides
    fun okHttpClientBuilder(): OkHttpClient.Builder = OkHttpClient.Builder()
        .connectTimeout(timeoutDuration, timeoutUnit)
        .readTimeout(timeoutDuration, timeoutUnit)
        .writeTimeout(timeoutDuration, timeoutUnit)

    private companion object {
        const val timeoutDuration = 1L
        val timeoutUnit = TimeUnit.MINUTES
    }
}

class NetworkStateImpl @Inject constructor(private val connectivityManager: ConnectivityManager) : NetworkState {

    override fun isNetworkConnected(): Boolean = connectivityManager.activeNetworkInfo?.isConnected ?: false
}
