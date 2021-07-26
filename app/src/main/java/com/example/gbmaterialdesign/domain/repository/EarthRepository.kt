package com.example.gbmaterialdesign.domain.repository

import com.example.gbmaterialdesign.data.model.EarthServerResponseData
import retrofit2.Call

interface EarthRepository {
    fun getDataEarth(): Call<List<EarthServerResponseData>>
}
