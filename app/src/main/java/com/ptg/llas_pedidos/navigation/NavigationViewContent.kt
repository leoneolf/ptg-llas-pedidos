package com.ptg.llas_pedidos.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ptg.llas_pedidos.R
import com.ptg.llas_pedidos.ui.components.BottomBarClient
import com.ptg.llas_pedidos.ui.components.BottomBarMaster
import com.ptg.llas_pedidos.ui.theme.LlasPedidosTheme
import com.ptg.llas_pedidos.ui.theme.Pink

/*
*  Definindo:
*   - conteúdos das telas
*/

@Composable
fun ViewContent(name: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text (
            modifier = Modifier.clickable { onClick() },
            text = name,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview
@Composable
fun ViewContentPreview() {
    LlasPedidosTheme(darkTheme = false) {
        ViewContent("Conteúdo que será exibido!") {}
    }
}

@Preview
@Composable
fun HomeViewContentPreview() {
    LlasPedidosTheme(darkTheme = false) {
        MasterViewContent(rememberNavController())
    }
}

@Composable
fun TesteSingInButton(
    onClick: () -> Unit
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
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp)
                .height(55.dp)
                .clickable {
                    onClick()
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

@Composable
fun LoginContent(
    onLoginClick: () -> Unit,
    onSingUpClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.clickable { onLoginClick() },
            text = "Master",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            modifier = Modifier.clickable { onSingUpClick() },
            text = "Cliente",
            fontSize = 28.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Preview
@Composable
fun LoginContentPreview() {
    LlasPedidosTheme() {
        LoginContent({},{})
    }
}

@Preview
@Composable
private fun TesteSingInButtonView() {
    TesteSingInButton{}
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MasterViewContent(navController: NavHostController = rememberNavController()) {
    Scaffold(
        bottomBar = { BottomBarMaster(navController = navController)}
    ) {
        MasterNavGraph(navController = navController)
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientViewContent(navController: NavHostController = rememberNavController()) {
    Scaffold(
        bottomBar = { BottomBarClient(navController = navController)}
    ) {
        ClientNavGraph(navController = navController)
    }
}