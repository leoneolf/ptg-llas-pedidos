package com.ptg.llas_pedidos.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ptg.llas_pedidos.R
import com.ptg.llas_pedidos.ui.theme.Pink

@Composable
fun SingInButton(
    onSingInClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Pink),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp)
                .height(55.dp)
                .clickable {
                    onSingInClick()
                },
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(width = 1.5.dp, color = Color.Black),
            elevation = 5.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .padding(start = 15.dp)
                        .size(32.dp)
                        .padding(0.dp)
                        .align(Alignment.CenterVertically),
                    painter = painterResource(id = R.drawable.ic_google_logo),
                    contentDescription = "google_logo"
                )
                Text(
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .align(Alignment.CenterVertically),
                    text = "Entrar com o Google",
                    fontSize = MaterialTheme.typography.h6.fontSize
                )
            }
        }
    }

}

@Preview
@Composable
private fun SingInButtonView() {
    SingInButton{}
}