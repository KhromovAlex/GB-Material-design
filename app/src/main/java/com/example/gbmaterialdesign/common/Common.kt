package com.example.gbmaterialdesign.common

import com.example.gbmaterialdesign.data.api.RetrofitClient
import com.example.gbmaterialdesign.data.api.RetrofitServices

object Common {
    var retrofitClient: RetrofitServices = RetrofitClient().getClient()
}
