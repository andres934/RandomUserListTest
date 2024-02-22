@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.randomusertest.ui.composables

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberAsyncImagePainter
import com.example.domain.model.UserData
import com.example.randomusertest.filterBySearch
import com.example.randomusertest.getItemsByFilter
import com.example.randomusertest.ui.navigation.AppScreens
import com.example.randomusertest.ui.states.ListUiState
import com.example.randomusertest.viewmodels.UsersListViewModel

@Composable
fun UserListScreen(
    navController: NavController,
    viewModel: UsersListViewModel = viewModel()
) {
    val listState = rememberLazyListState()
    val scrollState = rememberScrollState()
    val uiState by viewModel.uiState.collectAsState()
    var isSearching by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }

    LaunchedEffect(key1 = "MyKey") {
        viewModel.getRandomUsers()
    }

    Scaffold(
        topBar = {
            SearchableTopAppBar(
                isSearching = isSearching,
                searchText = searchText,
                onSearchClick = { isSearching = true },
                onSearchTextChange = { searchText = it },
                onCloseClick = {
                    isSearching = false
                    searchText = ""
                }
            )
        }
    ) {
        when (uiState) {
            is ListUiState.Success -> {
                RandomUsersListBody(
                    navController = navController,
                    listState = listState,
                    scrollState = scrollState,
                    paddingValues = it,
                    itemList = (uiState as ListUiState.Success).data.collectAsLazyPagingItems(),
                    isSearching = isSearching,
                    searchText = searchText
                )
            }

            is ListUiState.Loading -> {
                LoadingScreen()
            }

            is ListUiState.Error -> {
                ErrorScreen()
            }
        }
    }
}

@Composable
fun RandomUsersListBody(
    navController: NavController,
    listState: LazyListState,
    scrollState: ScrollState,
    paddingValues: PaddingValues,
    itemList: LazyPagingItems<UserData>,
    isSearching: Boolean,
    searchText: String
) {
    Box(
        modifier = Modifier.padding(paddingValues),
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(top = 12.dp, start = 12.dp)
                .scrollable(scrollState, Orientation.Vertical),
            state = listState
        ) {
            if (isSearching && searchText.isNotEmpty()) {
                items(count = itemList.itemSnapshotList.getItemsByFilter(searchText).size) {
                    itemList.itemSnapshotList.getItemsByFilter(searchText)[it]?.run {
                        RandomUserUIItem(
                            name = name,
                            lastName = lastName,
                            email = email,
                            gender = gender,
                            registryDate = registryDate,
                            phoneNumber = phoneNumber,
                            latitude = latitude,
                            longitude = longitude,
                            profilePicture = profilePicture,
                            onItemClicked = { fullName, email, gender, registryDate, phoneNumber, latitude, longitude, profilePicture ->
                                navController.navigate(
                                    AppScreens.UserDetails.route + "/$fullName/$email/$gender/$registryDate/$phoneNumber/$latitude/$longitude/${
                                        Uri.encode(
                                            profilePicture
                                        )
                                    }"
                                )
                            }
                        )
                    }
                }
            } else {
                items(count = itemList.itemCount) {
                    itemList[it].filterBySearch(isSearching, searchText)?.run {
                        RandomUserUIItem(
                            name = name,
                            lastName = lastName,
                            email = email,
                            gender = gender,
                            registryDate = registryDate,
                            phoneNumber = phoneNumber,
                            latitude = latitude,
                            longitude = longitude,
                            profilePicture = profilePicture,
                            onItemClicked = { fullName, email, gender, registryDate, phoneNumber, latitude, longitude, profilePicture ->
                                navController.navigate(
                                    AppScreens.UserDetails.route + "/$fullName/$email/$gender/$registryDate/$phoneNumber/$latitude/$longitude/${
                                        Uri.encode(
                                            profilePicture
                                        )
                                    }"
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun RandomUserUIItem(
    name: String,
    lastName: String,
    email: String,
    gender: String,
    registryDate: String,
    phoneNumber: String,
    latitude: String,
    longitude: String,
    profilePicture: String,
    onItemClicked: (String, String, String, String, String, String, String, String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = true) {
                onItemClicked(
                    "$name $lastName",
                    email,
                    gender,
                    registryDate,
                    phoneNumber,
                    latitude,
                    longitude,
                    profilePicture
                )
            }
            .testTag("ListItemContainer")
    ) {
        Card(
            modifier = Modifier
                .padding(end = 8.dp)
                .size(50.dp)
                .clip(CircleShape)
                .background(color = Color.Gray)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = rememberAsyncImagePainter(profilePicture),
                    contentDescription = "Profile Picture",
                    contentScale = ContentScale.Crop
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .weight(1f)
        ) {
            Text(
                text = "$name $lastName",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 6.dp)
            )

            Text(
                text = email,
                color = Color.LightGray,
            )
        }
        Icon(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(end = 8.dp),
            imageVector = Icons.Default.KeyboardArrowRight,
            tint = Color.LightGray,
            contentDescription = "ARROW_RIGHT"

        )

    }
    Divider(
        modifier = Modifier
            .padding(top = 12.dp, bottom = 12.dp, start = 58.dp),
        thickness = Dp.Hairline,
        color = Color.LightGray
    )
}

@Composable
fun SearchableTopAppBar(
    isSearching: Boolean,
    searchText: String,
    onSearchClick: () -> Unit,
    onSearchTextChange: (String) -> Unit,
    onCloseClick: () -> Unit
) {
    TopAppBar(
        title = {
            if (!isSearching) {
                Text(
                    text = "Contactos",
                    fontWeight = FontWeight.Bold
                )
            } else {
                SearchBar(
                    searchText = searchText,
                    onSearchTextChange = onSearchTextChange,
                    onCloseClick = onCloseClick
                )
            }
        },
        actions = {
            IconButton(
                onClick = onSearchClick
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                )
            }
        }
    )
}

@Composable
fun SearchBar(
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    onCloseClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onCloseClick) {
            Icon(Icons.Filled.ArrowBack, contentDescription = "CloseSearch")
        }
        TextField(
            value = searchText,
            onValueChange = onSearchTextChange,
            placeholder = {
                Text(
                    text = "Search",
                    fontWeight = FontWeight.Bold
                )
            },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                errorContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
            )
        )
    }
}

@Preview
@Composable
fun UserListScreenPreview() {
    UserListScreen(navController = rememberNavController())
}