package com.example.gbmaterialdesign.data.model

import com.google.gson.annotations.SerializedName

data class MarsPhotos(
    @SerializedName("img_src") val imgSrc: String?,
    @SerializedName("camera") val camera: MarsCamera?,
)
