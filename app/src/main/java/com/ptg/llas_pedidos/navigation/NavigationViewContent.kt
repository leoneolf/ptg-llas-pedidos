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
import com.ptg.llas_pedidos.ui.components.SingInButton
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
    LlasPedidosTheme() {
        ViewContent("Conteúdo que será exibido!") {}
    }
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