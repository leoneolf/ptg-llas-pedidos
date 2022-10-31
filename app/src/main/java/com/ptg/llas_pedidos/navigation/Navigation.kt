package com.ptg.llas_pedidos.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation

/*
*  Definindo:
*   - as rotas de navegação em si
*/

@Composable
fun RootNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = AppGraph.initial.ROOT,
        startDestination = AppGraph.auth.ROOT
    ) {
        authNavGraph(navController = navController)
        composable(route = AppGraph.master.ROOT) {
            MasterViewContent()
        }
        composable(route = AppGraph.client.ROOT) {
            ClientViewContent()
        }
    }
}

fun NavGraphBuilder.authNavGraph(navController: NavController) {
    navigation(
        route = AppGraph.auth.ROOT,
        startDestination = AppGraph.auth.LOGIN
    ) {
        composable(route = AppGraph.auth.LOGIN) {
            TesteSingInButton {
                navController.popBackStack()
                navController.navigate(AppGraph.auth.AUTH)
            }
        }
        composable(route = AppGraph.auth.AUTH) {
            LoginContent(
                onLoginClick = {
                    navController.navigate(AppGraph.master.ROOT)
                },
                onSingUpClick = {
                    navController.navigate(AppGraph.client.ROOT)
                }
            )
        }
    }
}

//private fun MainActivity.Companion.singIn() {
//    val googleSingInClient
//    val signInClient = googleSingInClient.signInIntent
//    startActivityForResult(signInClient, RC_SING_IN)
//}

@Composable
fun MasterNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = AppGraph.master.ROOT,
        startDestination = AppGraph.master.MENU
    ) {
        composable(route = AppGraph.master.MENU) {
            ViewContent(
                name = "Menu",
                onClick = {}
            )
        }
        composable(route = AppGraph.master.ORDER) {
            ViewContent(
                name = "Pedido",
                onClick = {}
            )
        }
        composable(route = AppGraph.master.SUPPORT) {
            ViewContent(
                name = "Suporte",
                onClick = {}
            )
        }
    }
}

@Composable
fun ClientNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = AppGraph.client.ROOT,
        startDestination = AppGraph.client.HOME
    ) {
        composable(route = AppGraph.client.HOME) {
            ViewContent(
                name = "Home",
                onClick = {}
            )
        }
        composable(route = AppGraph.client.MENU) {
            ViewContent(
                name = "Menu",
                onClick = {}
            )
        }
        composable(route = AppGraph.client.ORDER) {
            ViewContent(
                name = "Pedido",
                onClick = {}
            )
        }
        composable(route = AppGraph.client.SUPPORT) {
            ViewContent(
                name = "Suporte",
                onClick = {}
            )
        }
    }
}
