package com.eslam.vehicletracker.overview.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://gist.githubusercontent.com/pstued/13117a4f7c625ee9d96cbd876a6f1c9e/raw/7409b184955d05348e4718926a6599e5621b12c1/"

/**
 * Singleton [Retrofit] client
 */
val retrofit: Retrofit by lazy {
    Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

private val loggingInterceptor by lazy {
    HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BASIC
    }
}

private val okHttpClient: OkHttpClient by lazy {
    OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
}
