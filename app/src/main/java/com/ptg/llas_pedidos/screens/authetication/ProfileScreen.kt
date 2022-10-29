package com.ptg.llas_pedidos.screens.authetication

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ptg.llas_pedidos.ui.theme.Pink

@Composable
fun ProfileScreen(
    profileImage: Uri,
    name: String,
    email: String,
    singInAdminCliked: () -> Unit,
    singInClientCliked: () -> Unit,
    signOutClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Pink),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .size(150.dp)
                .fillMaxHeight(0.4f),
            shape = RoundedCornerShape(125.dp),
            elevation = 10.dp
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize(),
                model = profileImage,
                contentDescription = "profile_photo",
                contentScale = ContentScale.FillBounds
            )
        }

        Column(
            modifier = Modifier
                .fillMaxHeight(0.6f)
                .padding(top = 60.dp)
        ) {
            TextField(
                modifier = Modifier
                    .background(Color.LightGray),
                value = name,
                onValueChange = {},
                readOnly = true,
                label = {
                    Text(text = "Nome")
                },
            )

            TextField(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .background(Color.LightGray),
                value = email,
                onValueChange = {},
                readOnly = true,
                label = {
                    Text(text = "Email")
                },
            )
            Button(
                modifier = Modifier
                    .align(CenterHorizontally)
                    .padding(top = 10.dp),
                onClick = { singInAdminCliked() }
            ) {
                Text(text = "Entrar como Admin")
            }

            Button(
                modifier = Modifier
                    .align(CenterHorizontally)
                    .padding(top = 10.dp),
                onClick = { singInClientCliked() }
            ) {
                Text(text = "Entrar como Cliente")
            }

            Button(
                modifier = Modifier
                    .align(CenterHorizontally)
                    .padding(top = 10.dp),
                onClick = { signOutClicked() }
            ) {
                Text(text = "LogOut")
            }
        }
    }
}


@Composable
@Preview
private fun ProfileScreenPreview() {
//    ProfileScreen()
}