package com.ptg.llas_pedidos

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity

@Composable
fun AdminAuth() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var password by rememberSaveable { mutableStateOf("") }
        var passawordVisibility by remember { mutableStateOf(false) }
        
        val icon = if (passawordVisibility)
            painterResource(id = R.drawable.ic_visibility)
        else
            painterResource(id = R.drawable.ic_visibility_off)
        Text(text = "Insira a senha do usuário Master:", fontSize = 15.sp)
        OutlinedTextField(
            value = password, 
            onValueChange = {
                password = it
            },
            placeholder = { Text(text = "Senha") },
            label = { Text(text = "Senha") },
            trailingIcon = {
                IconButton(onClick = {
                    passawordVisibility = !passawordVisibility
                }) {
                    Icon(
                        painter = icon,
                        contentDescription = "Ícone de visibilidade"
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = if (passawordVisibility)
            VisualTransformation.None
            else PasswordVisualTransformation()
        )
        OutlinedButton(
            onClick = {
                if (password.equals("Abac@xi123")) {
                    val intent = Intent()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(35.dp),
        ) {
            Text(text = "Entrar")
        }
        
    }
}

@Composable
@Preview
fun AdminAuthPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AdminAuth()
    }
}