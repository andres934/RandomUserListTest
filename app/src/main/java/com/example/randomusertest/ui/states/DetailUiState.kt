package com.example.randomusertest.ui.states

import com.example.domain.model.UserData

sealed class DetailUiState {
    data object Idle : DetailUiState()

    data object Loading : DetailUiState()

    class Success(val data: UserData) : DetailUiState()

    class Error(val errorMsg: String) : DetailUiState()
}