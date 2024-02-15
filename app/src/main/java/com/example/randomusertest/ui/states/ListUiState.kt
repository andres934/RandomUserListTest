package com.example.randomusertest.ui.states

import androidx.paging.PagingData
import com.example.domain.model.UserData
import kotlinx.coroutines.flow.Flow

sealed class ListUiState {

    data object Loading : ListUiState()

    class Success(val data: Flow<PagingData<UserData>>) : ListUiState()

    class Error(val errorMsg: String) : ListUiState()
}