package com.example.data.internal

import com.example.data.model.RandomUserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

internal interface RandomUserService {

    @GET("api/")
    fun getUsers(
        @Query("results") results:Int?,
        @Query("page") page:Int?,
        @Query("seed") seed:String?
    ): Call<RandomUserResponse>

}