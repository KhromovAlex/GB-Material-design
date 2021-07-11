package com.example.gbmaterialdesign.domain.repository

import com.example.gbmaterialdesign.data.model.PODServerResponseData
import retrofit2.Call

interface PODRepository {
    fun getPOD(): Call<PODServerResponseData>
}
