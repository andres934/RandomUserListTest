package com.example.data.model

import com.squareup.moshi.Json

data class Location(

    @Json(name="country")
    val country: String? = null,

    @Json(name="city")
    val city: String? = null,

    @Json(name="street")
    val street: Street? = null,

    @Json(name="timezone")
    val timezone: Timezone? = null,

    @Json(name="postcode")
    val postcode: String? = null,

    @Json(name="coordinates")
    val coordinates: Coordinates? = null,

    @Json(name="state")
    val state: String? = null
)