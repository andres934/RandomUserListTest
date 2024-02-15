package com.example.randomusertest.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.data.RandomUserRepositoryImpl
import com.example.data.datasource.RemoteUsersDataSourceImpl
import com.example.domain.RandomUserUseCase
import com.example.randomusertest.ui.states.ListUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class UsersListViewModel(
    private val useCase: RandomUserUseCase = RandomUserUseCase(
        RandomUserRepositoryImpl(RemoteUsersDataSourceImpl())
    ),
    private val backgroundCoroutineContext: CoroutineContext = Dispatchers.IO
) : ViewModel() {

    private val _uiState = MutableStateFlow<ListUiState>(ListUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun getRandomUsers(query: String) {
        viewModelScope.launch(backgroundCoroutineContext) {
            runCatching {
                useCase.getRandomUsers(query).cachedIn(viewModelScope)
            }.fold(
                {
                    _uiState.emit(ListUiState.Success(it))
                }, {
                    _uiState.emit(
                        ListUiState.Error(
                            it.message ?: "Error calling random user service"
                        )
                    )
                }
            )
        }
    }
}