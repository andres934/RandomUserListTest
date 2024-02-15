package com.example.randomusertest.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.data.RandomUserRepositoryImpl
import com.example.data.datasource.RemoteUsersDataSourceImpl
import com.example.domain.RandomUserUseCase
import com.example.randomusertest.ui.states.DetailUiState
import com.example.randomusertest.ui.states.ListUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID
import kotlin.coroutines.CoroutineContext

class UsersDetailsViewModel(
    private val useCase: RandomUserUseCase = RandomUserUseCase(
        RandomUserRepositoryImpl(RemoteUsersDataSourceImpl())
    ),
    private val backgroundCoroutineContext: CoroutineContext = Dispatchers.IO
) : ViewModel() {

    private val _uiState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val uiState = _uiState.asStateFlow()

}