package com.ptg.llas_pedidos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AdminAuth() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var password by rememberSaveable { mutableStateOf("") }
        var passawordVisibility by remember { mutableStateOf(false) }
        
        val icon = if (passawordVisibility)
            painterResource(id = R.drawable.ic_visibility)
        else
            painterResource(id = R.drawable.ic_visibility_off)
        
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