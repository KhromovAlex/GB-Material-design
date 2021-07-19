package com.example.gbmaterialdesign.domain.repository

import com.example.gbmaterialdesign.common.Common.retrofitClient
import com.example.gbmaterialdesign.data.model.EarthServerResponseData
import retrofit2.Call

class EarthRepositoryImpl : EarthRepository {
    override fun getDataEarth(): Call<List<EarthServerResponseData>> {
        return retrofitClient.getDataEarth()
    }
}
