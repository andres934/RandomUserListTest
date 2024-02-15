package com.example.data

import com.example.data.model.UserDataResponse
import com.example.domain.model.UserData

fun UserDataResponse?.toUserData(): UserData =
    UserData(
        uuid = this?.login?.uuid.orEmpty(),
        title = this?.name?.title.orEmpty(),
        name = this?.name?.first.orEmpty(),
        lastName = this?.name?.last.orEmpty(),
        email = this?.email.orEmpty(),
        gender = this?.gender ?: "Not specified",
        registryDate = this?.registered?.date.orEmpty().getFormattedDateString(),
        phoneNumber = this?.cell.orEmpty(),
        latitude = this?.location?.coordinates?.latitude.orEmpty(),
        longitude = this?.location?.coordinates?.longitude.orEmpty(),
        profilePicture = this?.picture?.getSinglePicture().orEmpty(),
    )
