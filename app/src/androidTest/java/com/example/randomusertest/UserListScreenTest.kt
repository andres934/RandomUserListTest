package com.example.randomusertest

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.rememberNavController
import com.example.data.mocks.FakeRepositoryImpl
import com.example.data.mocks.defaultParsedUserData
import com.example.data.mocks.defaultParsedUserDataList
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

    @Test
    fun randomUserListShouldShowItemsWhenCallSucceeds() {
        val fakeRepository = FakeRepositoryImpl()
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

        rule.onNodeWithContentDescription("Search")
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

        rule.onNodeWithContentDescription("Search")
            .assertIsDisplayed()
            .assertHasClickAction()

        testDispatcher.scheduler.advanceUntilIdle()

        rule.onNodeWithTag("ErrorIndicatorContainer")
            .assertIsDisplayed()
    }

    @Test
    fun whenPressingSearchButtonSearchBarShouldShownAndHideTitle() {
        val fakeRepository = FakeRepositoryImpl()
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

        rule.onNodeWithContentDescription("Search")
            .assertIsDisplayed()
            .assertHasClickAction()
            .performClick()

        rule.onNodeWithText("Contactos")
            .assertDoesNotExist()

        rule.onNodeWithText("Search")
            .assertIsDisplayed()
            .assertIsEnabled()
    }

    @Test
    fun randomUserListShouldShowFilteredItemsWhenSearching() {
        val fakeRepository = FakeRepositoryImpl(expectedData = defaultParsedUserDataList)
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

        testDispatcher.scheduler.advanceUntilIdle()

        rule.onAllNodesWithTag("ListItemContainer")
            .assertCountEquals(5)

        rule.onAllNodesWithTag("ListItemContainer")[0]
            .assertIsDisplayed()
            .assertHasClickAction()
        rule.onAllNodesWithTag("ListItemContainer")[1]
            .assertIsDisplayed()
            .assertHasClickAction()
        rule.onAllNodesWithTag("ListItemContainer")[2]
            .assertIsDisplayed()
            .assertHasClickAction()
        rule.onAllNodesWithTag("ListItemContainer")[3]
            .assertIsDisplayed()
            .assertHasClickAction()
        rule.onAllNodesWithTag("ListItemContainer")[4]
            .assertIsDisplayed()
            .assertHasClickAction()

        rule.onNodeWithContentDescription("Search")
            .assertIsDisplayed()
            .assertHasClickAction()
            .performClick()

        // Typed search value in box
        rule.onNodeWithText("Search")
            .performTextInput("Vivian")

        val fullName = "${defaultParsedUserDataList[4].name} ${defaultParsedUserDataList[4].lastName}"

        // Check that now first item is the one searched
        rule.onAllNodesWithTag("ListItemContainer")[0]
            .assertIsDisplayed()
            .assertHasClickAction()
            .assertTextContains(fullName)
            .assertTextContains(defaultParsedUserDataList[4].email)

        // Other nodes all no longer displayed
        rule.onAllNodesWithTag("ListItemContainer")[1]
            .assertIsNotDisplayed()
        rule.onAllNodesWithTag("ListItemContainer")[2]
            .assertIsNotDisplayed()
        rule.onAllNodesWithTag("ListItemContainer")[3]
            .assertIsNotDisplayed()
        rule.onAllNodesWithTag("ListItemContainer")[4]
            .assertIsNotDisplayed()
    }

    @Test
    fun randomUserListShouldShowAllItemsWhenClosingSearchingBar() {
        val fakeRepository = FakeRepositoryImpl(expectedData = defaultParsedUserDataList)
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

        testDispatcher.scheduler.advanceUntilIdle()

        rule.onAllNodesWithTag("ListItemContainer")
            .assertCountEquals(5)

        rule.onAllNodesWithTag("ListItemContainer")[0]
            .assertIsDisplayed()
            .assertHasClickAction()
        rule.onAllNodesWithTag("ListItemContainer")[1]
            .assertIsDisplayed()
            .assertHasClickAction()
        rule.onAllNodesWithTag("ListItemContainer")[2]
            .assertIsDisplayed()
            .assertHasClickAction()
        rule.onAllNodesWithTag("ListItemContainer")[3]
            .assertIsDisplayed()
            .assertHasClickAction()
        rule.onAllNodesWithTag("ListItemContainer")[4]
            .assertIsDisplayed()
            .assertHasClickAction()

        rule.onNodeWithContentDescription("Search")
            .assertIsDisplayed()
            .assertHasClickAction()
            .performClick()

        // Typed search value in box
        rule.onNodeWithText("Search")
            .performTextInput("Vivian")

        val fullName = "${defaultParsedUserDataList[4].name} ${defaultParsedUserDataList[4].lastName}"

        // Check that now first item is the one searched
        rule.onAllNodesWithTag("ListItemContainer")[0]
            .assertIsDisplayed()
            .assertHasClickAction()
            .assertTextContains(fullName)
            .assertTextContains(defaultParsedUserDataList[4].email)

        // Other nodes all no longer displayed
        rule.onAllNodesWithTag("ListItemContainer")[1]
            .assertIsNotDisplayed()
        rule.onAllNodesWithTag("ListItemContainer")[2]
            .assertIsNotDisplayed()
        rule.onAllNodesWithTag("ListItemContainer")[3]
            .assertIsNotDisplayed()
        rule.onAllNodesWithTag("ListItemContainer")[4]
            .assertIsNotDisplayed()

        // Close the search bar
        rule.onNodeWithContentDescription("CloseSearch")
            .assertHasClickAction()
            .performClick()

        // All items displayed again
        rule.onAllNodesWithTag("ListItemContainer")[0]
            .assertIsDisplayed()
        rule.onAllNodesWithTag("ListItemContainer")[1]
            .assertIsDisplayed()
        rule.onAllNodesWithTag("ListItemContainer")[2]
            .assertIsDisplayed()
        rule.onAllNodesWithTag("ListItemContainer")[3]
            .assertIsDisplayed()
        rule.onAllNodesWithTag("ListItemContainer")[4]
            .assertIsDisplayed()
    }
}
