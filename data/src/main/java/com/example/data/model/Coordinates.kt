package com.example.data.model

import com.squareup.moshi.Json

data class Coordinates(

    @Json(name="latitude")
    val latitude: String? = null,

    @Json(name="longitude")
    val longitude: String? = null
)