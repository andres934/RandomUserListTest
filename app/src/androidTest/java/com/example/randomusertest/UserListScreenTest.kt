package com.example.randomusertest

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.rememberNavController
import com.example.data.mocks.FakeRepositoryImpl
import com.example.data.mocks.defaultParsedUserData
import com.example.domain.RandomUserUseCase
import com.example.randomusertest.ui.composables.UserListScreen
import com.example.randomusertest.viewmodels.UsersListViewModel
import kotlinx.coroutines.test.StandardTestDispatcher
import org.junit.Rule
import org.junit.Test

class UserListScreenTest {

    @get:Rule
    val rule = createComposeRule()

    private val testDispatcher = StandardTestDispatcher()
    private val fakeRepository = FakeRepositoryImpl()

    @Test
    fun randomUserListShouldShowItemsWhenCallSucceeds() {
        val useCase = RandomUserUseCase(fakeRepository)
        val viewModel = UsersListViewModel(
            useCase = useCase,
            backgroundCoroutineContext = testDispatcher
        )

        rule.setContent {
            UserListScreen(
                navController = rememberNavController(),
                viewModel = viewModel
            )
        }

        rule.onNodeWithText("Contactos")
            .assertIsDisplayed()

        rule.onNodeWithContentDescription("MORE")
            .assertIsDisplayed()
            .assertHasClickAction()

        testDispatcher.scheduler.advanceUntilIdle()

        rule.onAllNodesWithTag("ListItemContainer")
            .assertCountEquals(1)

        rule.onAllNodesWithTag("ListItemContainer")[0]
            .assertIsDisplayed()
            .assertHasClickAction()

        val fullName = "${defaultParsedUserData.name} ${defaultParsedUserData.lastName}"

        rule.onNodeWithText(fullName).assertIsDisplayed()
        rule.onNodeWithText(defaultParsedUserData.email).assertIsDisplayed()

        rule.onNodeWithContentDescription("Profile Picture")
            .assertIsDisplayed()
    }

    @Test
    fun randomUserListShouldShowErrorMsgWhenCallFails() {
        val useCase = RandomUserUseCase(FakeRepositoryImpl(shouldFail = true))
        val viewModel = UsersListViewModel(
            useCase = useCase,
            backgroundCoroutineContext = testDispatcher
        )

        rule.setContent {
            UserListScreen(
                navController = rememberNavController(),
                viewModel = viewModel
            )
        }

        rule.onNodeWithText("Contactos")
            .assertIsDisplayed()

        rule.onNodeWithContentDescription("MORE")
            .assertIsDisplayed()
            .assertHasClickAction()

        testDispatcher.scheduler.advanceUntilIdle()

        rule.onNodeWithTag("ErrorIndicatorContainer")
            .assertIsDisplayed()
    }
}
