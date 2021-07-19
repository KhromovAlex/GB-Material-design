package com.example.gbmaterialdesign.data.model

import com.google.gson.annotations.SerializedName

data class MarsCamera(
    @SerializedName("full_name") val fullName: String?,
    @SerializedName("name") val name: String?,
)
