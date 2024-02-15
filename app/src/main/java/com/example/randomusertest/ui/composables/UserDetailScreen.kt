package com.example.randomusertest.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.randomusertest.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailScreen(
    navController: NavController,
    fullName: String?,
    email: String?,
    gender: String?,
    registryDate: String?,
    phoneNumber: String?,
    latitude: String?,
    longitude: String?,
    profilePicture: String?
) {

    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .height(200.dp) // Adjust the height as needed
                    .fillMaxWidth()
            ) {
                // Background image
                Image(
                    painter = painterResource(id = R.mipmap.ic_banner_header),
                    contentDescription = "Header Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.TopCenter)
                )

                // TopAppBar
                TopAppBar(
                    modifier = Modifier
                        .paddingFromBaseline(top = 40.dp),
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent
                    ),
                    title = {
                        Text(
                            text = fullName ?: "Profile",
                            style = TextStyle(color = Color.White, fontWeight = FontWeight.Bold)
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Go back",
                                tint = Color.White
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { /* Handle menu icon click */ }) {
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = "Menu",
                                tint = Color.White
                            )
                        }
                    }
                )
                Image(
                    painter = rememberAsyncImagePainter(profilePicture),
                    contentDescription = "Profile picture",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .offset(x = 24.dp, y = 45.dp)
                        .clip(CircleShape)
                        .size(90.dp)
                        .border(2.dp, color = Color.White, shape = CircleShape)
                        .align(Alignment.BottomStart)
                )
            }
        },
        content = {
            Column(
                modifier = Modifier
                    .background(color = Color.White)
                    .padding(it)
                    .fillMaxSize()
            ) {
                Row(modifier = Modifier.align(Alignment.End)) {
                    IconButton(onClick = {}) {
                        Icon(
                            painterResource(id = R.drawable.outline_photo_camera_24),
                            contentDescription = "Edit Profile picture"
                        )
                    }
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Outlined.Edit,
                            contentDescription = "Edit information"
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .verticalScroll(scrollState)
                ) {
                    fullName?.run {
                        UserInfoField(
                            fieldIcon = Icons.Outlined.AccountCircle,
                            fieldTitle = "Nombre y Apellido",
                            fieldData = this
                        )
                    }
                    email?.run {
                        UserInfoField(
                            fieldIcon = Icons.Outlined.MailOutline,
                            fieldTitle = "Email",
                            fieldData = this
                        )
                    }
                    gender?.run {
                        UserInfoField(
                            fieldIcon = Icons.Outlined.Face,
                            fieldTitle = "Genero",
                            fieldData = this
                        )
                    }
                    registryDate?.run {
                        UserInfoField(
                            fieldIcon = Icons.Outlined.DateRange,
                            fieldTitle = "Fecha de registro",
                            fieldData = this
                        )
                    }
                    phoneNumber?.run {
                        UserInfoField(
                            fieldIcon = Icons.Outlined.Phone,
                            fieldTitle = "Telefono",
                            fieldData = this
                        )
                    }
                }
            }
        }
    )
}

@Composable
fun UserInfoField(
    fieldIcon: ImageVector,
    fieldTitle: String,
    fieldData: String
) {
    var textData by remember { mutableStateOf(fieldData) }

    Row(
        modifier = Modifier
            .background(Color.White)
            .padding(bottom = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.padding(start = 12.dp, top = 12.dp, bottom = 12.dp, end = 6.dp),
            imageVector = fieldIcon,
            contentDescription = fieldTitle
        )
        OutlinedTextField(
            modifier = Modifier
                .padding(start = 0.dp)
                .drawWithContent {
                    drawContent()
                    drawLine(
                        color = Color.LightGray, // Set border color
                        start = Offset(45f, size.height - 25),
                        end = Offset(size.width, size.height - 25),
                        strokeWidth = 1.dp.toPx() // Set border width
                    )
                },
            colors = TextFieldDefaults.colors(
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                errorContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
            ),
            textStyle = TextStyle(fontWeight = FontWeight.Bold),
            value = fieldData,
            onValueChange = {
                textData = it
            },
            label = {
                Text(text = fieldTitle, color = Color.LightGray)
            }
        )
    }
}

@Preview
@Composable
fun UserDetailScreenPreview() {
    UserDetailScreen(
        navController = rememberNavController(),
        fullName = "John Doe",
        email = "johndoe@example.com",
        gender = "Male",
        registryDate = "11/05/2018",
        phoneNumber = "+34 123456789",
        latitude = "654654635",
        longitude = "65135135",
        profilePicture = "https://randomuser.me/api/portraits/men/66.jpg"
    )
}