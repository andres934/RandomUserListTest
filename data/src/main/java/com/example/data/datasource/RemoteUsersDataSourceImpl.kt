package com.example.data.datasource

import android.accounts.NetworkErrorException
import android.util.Log
import com.example.data.internal.Api
import com.example.data.model.RandomUserResponse
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class RemoteUsersDataSourceImpl : RemoteUsersDataSource {

    override suspend fun getRandomUsers(
        result: Int,
        page: Int?,
        seed: String
    ) =
        suspendCancellableCoroutine { continuation ->
            val call = Api.retrofitService.getUsers(
                results = result,
                page = page,
                seed = seed
            )
            call.enqueue(object : Callback<RandomUserResponse> {
                override fun onResponse(
                    call: Call<RandomUserResponse>,
                    response: Response<RandomUserResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.apply {
                            continuation.resume(this)
                        }
                    } else {
                        Log.e(
                            "NetworkError",
                            response.errorBody()?.string() ?: "Error with random users call"
                        )
                        val error =
                            NetworkErrorException(
                                response.errorBody()?.string() ?: "Error with random users call"
                            )
                        continuation.resumeWithException(error)
                    }
                }

                override fun onFailure(call: Call<RandomUserResponse>, t: Throwable) {
                    val error =
                        NetworkErrorException(
                            t.message ?: "Error with random users call"
                        )
                    Log.e("NetworkError", t.message ?: "Error with random users call")
                    continuation.resumeWithException(error)
                }
            })
        }
}