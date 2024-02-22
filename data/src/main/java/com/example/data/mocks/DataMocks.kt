package com.example.data.mocks

import com.example.data.model.Coordinates
import com.example.data.model.Dob
import com.example.data.model.Id
import com.example.data.model.Info
import com.example.data.model.Location
import com.example.data.model.Login
import com.example.data.model.Name
import com.example.data.model.Picture
import com.example.data.model.RandomUserResponse
import com.example.data.model.Registered
import com.example.data.model.Street
import com.example.data.model.Timezone
import com.example.data.model.UserDataResponse
import com.example.domain.model.UserData

val defaultUserDataResponse = UserDataResponse(
    nat = "ES",
    gender = "female",
    phone = "+34 123 456 789",
    dob = Dob(
        date = "1992-03-08T15:13:16.688Z",
        age = 30
    ),
    name = Name(
        title = "Miss",
        first = "Jennie",
        last = "Nichols"
    ),
    registered = Registered(
        date = "2007-07-09T05:51:59.390Z",
        age = 14
    ),
    location = Location(
        street = Street(
            number = 8929,
            name = "Valwood Pkwy"
        ),
        city = "Billings",
        state = "Michigan",
        country = "United States",
        postcode = "63104",
        coordinates = Coordinates(
            latitude = "-69.8246",
            longitude = "134.8719"
        ),
        timezone = Timezone(
            offset = "+9:30",
            description = "Adelaide, Darwin"
        )
    ),
    id = Id(
        name = "SSN",
        value = "405-88-3636"
    ),
    login = Login(
        uuid = "7a0eed16-9430-4d68-901f-c0d4c1c3bf00",
        username = "yellowpeacock117",
        password = "addison",
        salt = "sld1yGtd",
        md5 = "ab54ac4c0be9480ae8fa5e9e2a5196a3",
        sha1 = "edcf2ce613cbdea349133c52dc2f3b83168dc51b",
        sha256 = "48df5229235ada28389b91e60a935e4f9b73eb4bdb855ef9258a1751f10bdc5d"
    ),
    cell = "(489) 330-2385",
    email = "jennie.nichols@example.com",
    picture = Picture(
        large = "https://randomuser.me/api/portraits/men/75.jpg",
        medium = "https://randomuser.me/api/portraits/med/men/75.jpg",
        thumbnail = "https://randomuser.me/api/portraits/thumb/men/75.jpg"
    )
)

val defaultRandomUserResponse = RandomUserResponse(
    results = listOf(defaultUserDataResponse),
    info = Info(
        seed = "seedtest",
        results = 1,
        page = 1,
        version = "1.4"
    )
)

val defaultParsedUserData = UserData(
    uuid = "7a0eed16-9430-4d68-901f-c0d4c1c3bf00",
    title = "Miss",
    name = "Jennie",
    lastName = "Nichols",
    email = "jennie.nichols@example.com",
    gender = "female",
    registryDate = "09-07-2007",
    phoneNumber = "(489) 330-2385",
    latitude = "-69.8246",
    longitude = "134.8719",
    profilePicture = "https://randomuser.me/api/portraits/men/75.jpg"
)

val defaultParsedUserDataList =
    listOf(
        defaultParsedUserData,
        defaultParsedUserData.copy(name = "Maria", lastName = "Ramirez", email = "maria.ramirez@example.com"),
        defaultParsedUserData.copy(name = "John", lastName = "Doe", email = "john.doe@example.com"),
        defaultParsedUserData.copy(name = "Ali", lastName = "Dyrdal", email = "ali.dyrdal@example.com"),
        defaultParsedUserData.copy(name = "Vivian", lastName = "Flores", email = "vivian.flores@example.com")
    )