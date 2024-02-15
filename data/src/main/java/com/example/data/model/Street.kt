package com.example.data.model

import com.squareup.moshi.Json

data class Street(

    @Json(name="number")
    val number: Int? = null,

    @Json(name="name")
    val name: String? = null
)