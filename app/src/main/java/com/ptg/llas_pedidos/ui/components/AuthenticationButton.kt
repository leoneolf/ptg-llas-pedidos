package com.ptg.llas_pedidos.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ptg.llas_pedidos.ui.theme.Pink

@Composable
fun AuthenticationButton(
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
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp)
        ) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                onClick = { singInAdminCliked() }
            ){
                Text(text = "Entrar como Admin")
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                onClick = { singInClientCliked() }
            ){
                Text(text = "Entrar como Cliente")
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                onClick = { signOutClicked() }
            ){
                Text(text = "LogOut")
            }

            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Composable
@Preview
private fun AuthenticationButtonPreview() {
    AuthenticationButton({},{},{})
}