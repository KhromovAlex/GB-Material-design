package com.example.gbmaterialdesign.data.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class EarthServerResponseData(
    @SerializedName("image") val image: String?,
    @SerializedName("date") val date: Date?,
    @SerializedName("caption") val title: String?,
)
