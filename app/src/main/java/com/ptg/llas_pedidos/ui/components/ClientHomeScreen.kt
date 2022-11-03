package com.ptg.llas_pedidos.ui.components

import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ptg.llas_pedidos.ui.theme.Pink

@Composable
fun ClientHomeScreen(
    profileImage: Uri,
    name: String,
    email: String
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
            border = BorderStroke(
                width = 2.dp,
                color = Color.Black
            ),
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

        }
    }
}


@Composable
@Preview
private fun ClientHomeScreenPreview() {
//    ClientHomeScreen(
//        profileImage = ,
//        name = "teste",
//        email = "teste"
//    )
}