package com.ptg.llas_pedidos.authetication

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.ptg.llas_pedidos.R

@Composable
fun AdminAuth(
    disgraca: () -> Unit
) {
    val navController = rememberNavController()
    val context =  LocalContext.current


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

//        val mContext = LocalContext.current
        OutlinedButton(
            onClick = {
                      if (password.equals("1234")){
                          disgraca ()
                      } else {
                          Toast.makeText(context, "Senha errada!", Toast.LENGTH_SHORT).show()
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
        AdminAuth (){}
    }
}