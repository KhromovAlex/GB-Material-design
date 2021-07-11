package com.example.gbmaterialdesign.domain.repository

import com.example.gbmaterialdesign.data.api.RetrofitClient
import com.example.gbmaterialdesign.data.model.PODServerResponseData
import retrofit2.Call

class PODRepositoryImpl : PODRepository {
    private val retrofit = RetrofitClient()

    override fun getPOD(): Call<PODServerResponseData> {
        return retrofit.getClient().getPictureOfTheDay()
    }

}
