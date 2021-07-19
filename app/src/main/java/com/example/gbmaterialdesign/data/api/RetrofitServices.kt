package com.example.gbmaterialdesign.data.api

import com.example.gbmaterialdesign.data.model.PODServerResponseData
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitServices {
    @GET("planetary/apod")
    fun getPictureOfTheDay(): Call<PODServerResponseData>
}
