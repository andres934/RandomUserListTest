package com.example.data.model

import com.squareup.moshi.Json

data class Picture(

    @Json(name = "thumbnail")
    val thumbnail: String? = null,

    @Json(name = "large")
    val large: String? = null,

    @Json(name = "medium")
    val medium: String? = null
) {
    fun getSinglePicture() = if (!large.isNullOrEmpty()) {
        large
    } else if (!medium.isNullOrEmpty()) {
        medium
    } else {
        thumbnail
    }
}