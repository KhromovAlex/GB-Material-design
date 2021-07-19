package com.example.gbmaterialdesign.domain.repository

import com.example.gbmaterialdesign.common.Common.retrofitClient
import com.example.gbmaterialdesign.data.model.MarsServerResponseData
import retrofit2.Call
import java.util.*

class MarsRepositoryImpl : MarsRepository {
    override fun getDataMars(): Call<MarsServerResponseData> {
        val calendar = Calendar.getInstance()
        val dateString =
            "${calendar[Calendar.YEAR]}-${calendar[Calendar.MONTH] + 1}-${calendar[Calendar.DAY_OF_MONTH] - 1}"
        return retrofitClient.getDataMars(dateString)
    }
}
