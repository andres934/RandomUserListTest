package com.example.domain

import androidx.paging.PagingData
import com.example.domain.model.UserData
import kotlinx.coroutines.flow.Flow

class RandomUserUseCase(
    private val repository: RandomUserRepository
) {

    suspend fun getRandomUsers(): Flow<PagingData<UserData>> =
        repository.getRandomUsers()

}