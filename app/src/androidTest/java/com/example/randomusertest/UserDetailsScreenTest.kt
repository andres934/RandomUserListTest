package com.example.randomusertest

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.rememberNavController
import com.example.data.mocks.defaultParsedUserData
import com.example.randomusertest.ui.composables.UserDetailScreen
import org.junit.Rule
import org.junit.Test

class UserDetailsScreenTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun onDetailsOpenedShouldShowAllReceivedUserInformation() {
        val fullName = "${defaultParsedUserData.name} ${defaultParsedUserData.lastName}"

        rule.setContent {
            UserDetailScreen(
                navController = rememberNavController(),
                fullName = fullName,
                email = defaultParsedUserData.email,
                gender = defaultParsedUserData.gender,
                registryDate = defaultParsedUserData.registryDate,
                phoneNumber = defaultParsedUserData.phoneNumber,
                latitude = defaultParsedUserData.latitude,
                longitude = defaultParsedUserData.longitude,
                profilePicture = defaultParsedUserData.profilePicture,
            )
        }

        rule.onNodeWithContentDescription("Header Image")
            .assertIsDisplayed()

        rule.onNodeWithContentDescription("Go back")
            .assertIsDisplayed()
            .assertHasClickAction()

        rule.onNodeWithContentDescription("Menu")
            .assertIsDisplayed()
            .assertHasClickAction()

        rule.onNodeWithContentDescription("Profile picture")
            .assertIsDisplayed()

        rule.onNodeWithContentDescription("Edit Profile picture")
            .assertIsDisplayed()
            .assertHasClickAction()

        rule.onNodeWithContentDescription("Edit information")
            .assertIsDisplayed()
            .assertHasClickAction()

        // TopAppBar Title
        with(rule.onAllNodesWithText(fullName)[0]) {
            assertIsDisplayed()
        }

        // Name Field
        with(rule.onAllNodesWithText(fullName)[1]) {
            assertIsDisplayed()
        }

        rule.onNodeWithText("Email")
            .assertIsDisplayed()
            .assertTextContains(defaultParsedUserData.email)

        rule.onNodeWithText("Genero")
            .assertIsDisplayed()
            .assertTextContains(defaultParsedUserData.gender)

        rule.onNodeWithText("Fecha de registro")
            .assertIsDisplayed()
            .assertTextContains(defaultParsedUserData.registryDate)

        rule.onNodeWithText("Telefono")
            .assertIsDisplayed()
            .assertTextContains(defaultParsedUserData.phoneNumber)
    }

    @Test
    fun onDetailsOpenedWithNullNameShouldDifferentTitleAndHideNameFieldShowAllReceivedUserInformation() {
        rule.setContent {
            UserDetailScreen(
                navController = rememberNavController(),
                fullName = null,
                email = defaultParsedUserData.email,
                gender = defaultParsedUserData.gender,
                registryDate = defaultParsedUserData.registryDate,
                phoneNumber = defaultParsedUserData.phoneNumber,
                latitude = defaultParsedUserData.latitude,
                longitude = defaultParsedUserData.longitude,
                profilePicture = defaultParsedUserData.profilePicture,
            )
        }

        rule.onNodeWithText("Profile")
            .assertIsDisplayed()

        rule.onNodeWithText("Nombre y Apellido")
            .assertDoesNotExist()
    }
}
