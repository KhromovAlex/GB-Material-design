package com.example.gbmaterialdesign.domain.repository

import com.example.gbmaterialdesign.data.model.MarsServerResponseData
import retrofit2.Call

interface MarsRepository {
    fun getDataMars(): Call<MarsServerResponseData>
}
