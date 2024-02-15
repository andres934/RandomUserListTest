package com.example.domain

import androidx.paging.PagingData
import com.example.domain.model.UserData
import kotlinx.coroutines.flow.Flow

interface RandomUserRepository {
    suspend fun getRandomUsers(query: String = "?results=30"): Flow<PagingData<UserData>>
}