package com.example.gbmaterialdesign.data.model

import com.google.gson.annotations.SerializedName

data class MarsServerResponseData(
    @SerializedName("photos") val photos: List<MarsPhotos>?,
)
