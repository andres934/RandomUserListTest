package com.example.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.data.datasource.RemoteUsersDataSource
import com.example.domain.RandomUserRepository
import com.example.domain.model.UserData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RandomUserRepositoryImpl(
    private val remoteDataSource: RemoteUsersDataSource
): RandomUserRepository {

    companion object {
        private const val INITIAL_PAGE_KEY = 1
        private const val PREFETCH_DISTANCE = 15
        private const val PAGE_SIZE = 30
    }

    override suspend fun getRandomUsers(): Flow<PagingData<UserData>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                prefetchDistance = PREFETCH_DISTANCE
            ),
            initialKey = INITIAL_PAGE_KEY,
            pagingSourceFactory = { RandomUserPagingSource(remoteDataSource) }
        ).flow.map { pagingData ->
            pagingData.map {
                it.toUserData()
            }
        }
    }
}
