package com.example.randomusertest

import androidx.paging.PagingData
import androidx.paging.map
import com.example.data.mocks.defaultParsedUserData
import com.example.domain.RandomUserUseCase
import com.example.randomusertest.ui.states.ListUiState
import com.example.randomusertest.viewmodels.UsersListViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class UsersListViewModelTest {

    private val testDispatcher = UnconfinedTestDispatcher()
    private val mockUseCase = mockk<RandomUserUseCase>()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun `on ViewModel initialized uiState starts with Loading as default`() {
        val mockUseCase = mockk<RandomUserUseCase>()
        val viewModel = UsersListViewModel(mockUseCase, testDispatcher)

        assert(viewModel.uiState.value is ListUiState.Loading)
    }

    @Test
    fun `getRandomUsers emits success state with data`() = runTest(testDispatcher) {
        val mockPagingData = flowOf(PagingData.from(listOf(defaultParsedUserData)))

        // Mock behavior of use case
        coEvery { mockUseCase.getRandomUsers() } returns mockPagingData

        // Create UsersListViewModel instance with mock use case
        val viewModel = UsersListViewModel(mockUseCase, testDispatcher)

        // Call the getRandomUsers function
        viewModel.getRandomUsers()

        assert(viewModel.uiState.value is ListUiState.Success)
        (viewModel.uiState.first() as ListUiState.Success).data.first().map {
            assertEquals(it, defaultParsedUserData)
        }

        coVerify { mockUseCase.getRandomUsers() }
    }

    @Test
    fun `getRandomUsers emits error state`() = runTest(testDispatcher) {
        // Mock error message
        val errorMessage = "Error calling random user service"

        // Mock behavior of use case
        coEvery { mockUseCase.getRandomUsers() } throws Exception(errorMessage)

        // Create UsersListViewModel instance with mock use case
        val viewModel = UsersListViewModel(mockUseCase, testDispatcher)

        // Call the getRandomUsers function
        viewModel.getRandomUsers()

        // Verify that the viewModel emits error state with correct error message
        assert(viewModel.uiState.value is ListUiState.Error)
        assertEquals((viewModel.uiState.value as ListUiState.Error).errorMsg, errorMessage)

        // Verify that the use case method was called with the correct parameter
        coVerify { mockUseCase.getRandomUsers() }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}