package com.ptg.llas_pedidos

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ptg.llas_pedidos.screens.*

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ){
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen()
        }
        composable(route = BottomBarScreen.Cardapio.route) {
            CardapioScreen()
        }
        composable(route = BottomBarScreen.Pedido.route) {
            PedidoScreen()
        }
        composable(route = BottomBarScreen.Suporte.route) {
            SuporteScreen()
        }
        composable(route = BottomBarScreen.Cliente.route) {
            ClienteScreen()
        }
    }
}