package com.example.domain

import androidx.paging.PagingData
import com.example.domain.model.UserData
import kotlinx.coroutines.flow.Flow

interface RandomUserRepository {
    suspend fun getRandomUsers(): Flow<PagingData<UserData>>
}