package com.example.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.datasource.RemoteUsersDataSource
import com.example.data.model.UserDataResponse
import okio.IOException
import retrofit2.HttpException

internal class RandomUserPagingSource(
    private val remoteDataSource: RemoteUsersDataSource
): PagingSource<Int, UserDataResponse>() {

    override fun getRefreshKey(state: PagingState<Int, UserDataResponse>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserDataResponse> {
        return try {
            val currentPage = params.key ?: 1
            val expectedResults = 30
            val randomUsers = remoteDataSource.getRandomUsers(
                result = expectedResults,
                page = currentPage
            )
            val nextKey = currentPage + 1

            LoadResult.Page(
                data = randomUsers.results.distinctBy { it.login?.uuid },
                prevKey = null,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}