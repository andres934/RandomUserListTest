package com.example.data.model

import com.squareup.moshi.Json

data class Name(

    @Json(name="last")
    val last: String? = null,

    @Json(name="title")
    val title: String? = null,

    @Json(name="first")
    val first: String? = null
)