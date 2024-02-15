package com.example.data.model

import com.squareup.moshi.Json

data class Registered(

    @Json(name="date")
    val date: String? = null,

    @Json(name="age")
    val age: Int? = null
)