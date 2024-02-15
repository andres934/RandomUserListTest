package com.example.data.model

import com.squareup.moshi.Json

data class RandomUserResponse(
    @Json(name = "results")
    val results: List<UserDataResponse> = emptyList(),

    @Json(name = "info")
    val info: Info? = null
)
