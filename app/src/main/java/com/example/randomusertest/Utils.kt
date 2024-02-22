package com.example.randomusertest

import androidx.paging.ItemSnapshotList
import com.example.domain.model.UserData


fun String.isSimilar(searchString: String) = contains(searchString, ignoreCase = true)

fun UserData?.filterBySearch(isSearching: Boolean, searchString: String): UserData? =
    if (this != null && isSearching && searchString.isNotEmpty()) {
        filterOrNull(searchString)
    } else {
        this
    }

fun ItemSnapshotList<UserData>.getItemsByFilter(searchString: String) =
    filter { it?.run { userMatches(searchString) } ?: false }

private fun UserData.userMatches(searchString: String) =
    "$name $lastName".isSimilar(searchString) || email.isSimilar(searchString)

private fun UserData.filterOrNull(searchString: String) =
    if (userMatches(searchString)) this
    else null
