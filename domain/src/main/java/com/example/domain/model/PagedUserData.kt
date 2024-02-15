package com.example.domain.model

data class PagedUserData(
    val seed: String,
    val results: Int,
    val page: Int,
    val data: List<UserData>
)
