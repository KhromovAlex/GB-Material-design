package com.example.gbmaterialdesign.domain.repository

import com.example.gbmaterialdesign.common.Common.retrofitClient
import com.example.gbmaterialdesign.data.model.PODServerResponseData
import retrofit2.Call

class PODRepositoryImpl : PODRepository {
    override fun getPOD(): Call<PODServerResponseData> {
        return retrofitClient.getPictureOfTheDay()
    }
}
