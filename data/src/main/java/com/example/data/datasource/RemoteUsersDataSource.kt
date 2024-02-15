package com.example.data.datasource

import com.example.data.model.RandomUserResponse

interface RemoteUsersDataSource {
    suspend fun getRandomUsers(
        result: Int = 30,
        page: Int? = null,
        seed: String = "randomusertest"
    ): RandomUserResponse
}