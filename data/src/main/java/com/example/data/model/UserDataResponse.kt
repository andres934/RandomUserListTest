package com.example.data.model

import com.squareup.moshi.Json

data class UserDataResponse(

	@Json(name="nat")
	val nat: String? = null,

	@Json(name="gender")
	val gender: String? = null,

	@Json(name="phone")
	val phone: String? = null,

	@Json(name="dob")
	val dob: Dob? = null,

	@Json(name="name")
	val name: Name? = null,

	@Json(name="registered")
	val registered: Registered? = null,

	@Json(name="location")
	val location: Location? = null,

	@Json(name="id")
	val id: Id? = null,

	@Json(name="login")
	val login: Login? = null,

	@Json(name="cell")
	val cell: String? = null,

	@Json(name="email")
	val email: String? = null,

	@Json(name="picture")
	val picture: Picture? = null
)
