package com.example.gbmaterialdesign.data.api

import com.example.gbmaterialdesign.data.model.EarthServerResponseData
import com.example.gbmaterialdesign.data.model.MarsServerResponseData
import com.example.gbmaterialdesign.data.model.PODServerResponseData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitServices {
    @GET("planetary/apod")
    fun getPictureOfTheDay(): Call<PODServerResponseData>

    @GET("EPIC/api/natural")
    fun getDataEarth(): Call<List<EarthServerResponseData>>

    @GET("mars-photos/api/v1/rovers/curiosity/photos")
    fun getDataMars(@Query("earth_date") data: String): Call<MarsServerResponseData>
}
