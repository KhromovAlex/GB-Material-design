package com.example.gbmaterialdesign.data.api

import com.example.gbmaterialdesign.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    fun getClient(): RetrofitServices {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(createHttpClient())
            .build()

        return retrofit.create(RetrofitServices::class.java)
    }

    private fun createHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient
            .addInterceptor(HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) })
            .addInterceptor {
                it.proceed(
                    it.request()
                        .newBuilder()
                        .url(
                            it.request().url.newBuilder()
                                .addQueryParameter("api_key", BuildConfig.NASA_API_KEY).build()
                        )
                        .build()
                )
            }

        return httpClient.build()
    }
}
