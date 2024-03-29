package com.example.data.mocks

import androidx.paging.PagingData
import com.example.domain.RandomUserRepository
import com.example.domain.model.UserData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeRepositoryImpl(
    private val shouldFail: Boolean = false,
    private val expectedData: List<UserData> = listOf(defaultParsedUserData)) : RandomUserRepository {
    override suspend fun getRandomUsers(): Flow<PagingData<UserData>> {
        if (shouldFail)
            throw Throwable("This is an error")
        return flowOf(PagingData.from(expectedData))
    }
}