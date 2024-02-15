package com.example.data.model

import com.squareup.moshi.Json

data class Info(

    @Json(name = "seed")
    val seed: String? = null,

    @Json(name = "results")
    val results: Int? = null,

    @Json(name = "page")
    val page: Int? = null,

    @Json(name = "version")
    val version: String? = null,
)
