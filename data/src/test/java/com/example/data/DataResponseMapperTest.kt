package com.example.data

import com.example.data.mocks.defaultParsedUserData
import com.example.data.mocks.defaultUserDataResponse
import org.junit.Test

class DataResponseMapperTest {

    @Test
    fun `on UserDataResponse mapping attempt should return expected UserData`() {
        // When
        val userData = defaultUserDataResponse.toUserData()

        // Then
        assert(userData == defaultParsedUserData)
    }

    @Test
    fun `on UserDataResponse mapping attempt without gender should return expected UserData`() {
        // Given
        val expectedUserDataResponse = defaultUserDataResponse.copy(gender = null)
        val expectedParsedUserData = defaultParsedUserData.copy(gender = "Not specified")

        // When
        val userData = expectedUserDataResponse.toUserData()

        // Then
        assert(userData == expectedParsedUserData)
    }

    @Test
    fun `on UserDataResponse mapping attempt without large picture should return expected UserData`() {
        // Given
        val expectedUserDataResponse = defaultUserDataResponse.copy(
            picture = defaultUserDataResponse.picture?.copy(
                thumbnail = "https://randomuser.me/api/portraits/thumb/men/75.jpg",
                medium = "https://randomuser.me/api/portraits/med/men/75.jpg",
                large = null
            )
        )
        val expectedParsedUserData =
            defaultParsedUserData.copy(
                profilePicture = "https://randomuser.me/api/portraits/med/men/75.jpg"
            )

        // When
        val userData = expectedUserDataResponse.toUserData()

        // Then
        assert(userData == expectedParsedUserData)
    }

    @Test
    fun `on UserDataResponse mapping attempt without large or medium picture should return expected UserData`() {
        // Given
        val expectedUserDataResponse = defaultUserDataResponse.copy(
            picture = defaultUserDataResponse.picture?.copy(
                thumbnail = "https://randomuser.me/api/portraits/thumb/men/75.jpg",
                medium = null,
                large = null
            )
        )
        val expectedParsedUserData =
            defaultParsedUserData.copy(
                profilePicture = "https://randomuser.me/api/portraits/thumb/men/75.jpg"
            )

        // When
        val userData = expectedUserDataResponse.toUserData()

        // Then
        assert(userData == expectedParsedUserData)
    }

}