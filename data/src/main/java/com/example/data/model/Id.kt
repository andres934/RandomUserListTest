package com.example.data.model

import com.squareup.moshi.Json

data class Id(

    @Json(name="name")
    val name: String? = null,

    @Json(name="value")
    val value: String? = null
)