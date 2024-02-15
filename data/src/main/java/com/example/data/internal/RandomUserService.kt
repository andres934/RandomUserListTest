package com.example.data.internal

import com.example.data.model.RandomUserResponse
import com.example.data.model.UserDataResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryName
import retrofit2.http.Url

internal interface RandomUserService {

    @GET("api/")
    fun getUsers(
        @Query("results") results:Int?,
        @Query("page") page:Int?,
        @Query("seed") seed:String?
    ): Call<RandomUserResponse>

    @GET
    fun getStaticMapSnapshot(@Url url: String = "https://maps.geoapify.com/v1/staticmap?style=osm-bright-smooth&width=600&height=400&center=lonlat%3A-122.29009844646316%2C47.54607447032754&zoom=14.3497&marker=lonlat%3A-122.29188334609739%2C47.54403990655936%3Btype%3Aawesome%3Bcolor%3A%23bb3f73%3Bsize%3Ax-large%3Bicon%3Apaw%7Clonlat%3A-122.29282631194182%2C47.549609195001494%3Btype%3Amaterial%3Bcolor%3A%234c905a%3Bicon%3Atree%3Bicontype%3Aawesome%7Clonlat%3A-122.28726954893025%2C47.541766557545884%3Btype%3Amaterial%3Bcolor%3A%234c905a%3Bicon%3Atree%3Bicontype%3Aawesome&apiKey=45d7e55ba20b44718bf8b33ed3cd116c")

}