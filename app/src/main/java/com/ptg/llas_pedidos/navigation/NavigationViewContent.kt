package com.ptg.llas_pedidos.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ptg.llas_pedidos.ui.components.BottomBar
import com.ptg.llas_pedidos.ui.theme.LlasPedidosTheme

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

//@Preview
//@Composable
//fun HomeViewContentPreview() {
//    LlasPedidosTheme(darkTheme = false) {
//        HomeViewContent(rememberNavController())
//    }
//}

@Composable
fun LoginContent(
    onLoginClick: () -> Unit,
    onSingUpClick: () -> Unit,
    onSingOutClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.clickable { onLoginClick() },
            text = "Login",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            modifier = Modifier.clickable { onSingUpClick() },
            text = "Sing Up",
            fontSize = 28.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            modifier = Modifier.clickable { onSingOutClick() },
            text = "Sing Out",
            fontSize = 28.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Preview
@Composable
fun LoginContentPreview() {
    LlasPedidosTheme(darkTheme = false) {
        LoginContent({},{},{})
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainViewContent(navController: NavHostController = rememberNavController()) {
    Scaffold(
        bottomBar = { BottomBar(navController = navController)}
    ) {
        MainNavGraph(navController = navController)
    }
}