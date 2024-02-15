package com.example.data

import androidx.paging.PagingSource
import com.example.data.datasource.RemoteUsersDataSource
import com.example.data.mocks.defaultRandomUserResponse
import com.example.data.mocks.defaultUserDataResponse
import com.example.data.model.UserDataResponse
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import okio.IOException
import org.junit.Test

class RandomUserPagingSourceTest {

    private val mockRemoteDataSource: RemoteUsersDataSource = mockk()

    @Test
    fun `load returns expected LoadResult Page`() = runTest {
        // Given
        coEvery { mockRemoteDataSource.getRandomUsers(any(), any()) } returns defaultRandomUserResponse
        val pagingSource = RandomUserPagingSource(mockRemoteDataSource)

        // When
        val loadParams = PagingSource.LoadParams.Refresh(1, 30, false)
        val loadResult = pagingSource.load(loadParams)

        // Then
        assert(loadResult is PagingSource.LoadResult.Page)
        val pageResult = loadResult as PagingSource.LoadResult.Page<Int, UserDataResponse>
        assertEquals(2, pageResult.nextKey)
        assertEquals(defaultUserDataResponse, pageResult.data[0])
    }

    @Test
    fun `load returns LoadResult Error on exception`() = runTest {
        // Given
        coEvery { mockRemoteDataSource.getRandomUsers(any(), any()) } throws IOException()
        val pagingSource = RandomUserPagingSource(mockRemoteDataSource)

        // When
        val loadParams = PagingSource.LoadParams.Refresh(1, 30, false)
        val loadResult = pagingSource.load(loadParams)

        // Then
        assert(loadResult is PagingSource.LoadResult.Error)
    }
}