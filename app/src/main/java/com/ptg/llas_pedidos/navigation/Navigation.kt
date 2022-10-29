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
        composable(route = AppGraph.main.ROOT) {
            MainViewContent()
        }
    }
}

fun NavGraphBuilder.authNavGraph(navController: NavController) {
    navigation(
        route = AppGraph.auth.ROOT,
        startDestination = AppGraph.auth.LOGIN
    ) {
        composable(route = AppGraph.auth.LOGIN) {
            LoginContent(
                onLoginClick = {
                    navController.popBackStack()
                    navController.navigate(AppGraph.main.ROOT)
                },
                onSingUpClick = { navController.navigate(AppGraph.auth.SING_UP) },
                onSingOutClick = { navController.navigate(AppGraph.auth.SING_OUT) }
            )
        }
        composable(route = AppGraph.auth.SING_UP) {
            ViewContent(name = "Sing Up") {}
        }
        composable(route = AppGraph.auth.SING_OUT) {
            ViewContent(name = "Sing Out") {}
        }
    }
}

@Composable
fun MainNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = AppGraph.main.ROOT,
        startDestination = AppGraph.main.HOME
    ) {
        composable(route = AppGraph.main.HOME) {
            ViewContent(
                name = "Home",
                onClick = {
                    navController.navigate(AppGraph.detailsHome.ROOT)
                }
            )
        }
        composable(route = AppGraph.main.MENU) {
            ViewContent(
                name = "Menu",
                onClick = {}
            )
        }
        composable(route = AppGraph.main.ORDER) {
            ViewContent(
                name = "Pedido",
                onClick = {}
            )
        }
        composable(route = AppGraph.main.SUPPORT) {
            ViewContent(
                name = "Suporte",
                onClick = {}
            )
        }
        composable(route = AppGraph.main.CLIENT) {
            ViewContent(
                name = "Cliente",
                onClick = {}
            )
        }
        detailsHomeGraph(navController = navController)
    }
}

fun NavGraphBuilder.detailsHomeGraph(navController: NavController) {
    navigation(
        route = AppGraph.detailsHome.ROOT,
        startDestination = AppGraph.detailsHome.HELP
    ) {
        composable(route = AppGraph.detailsHome.HELP) {
            ViewContent(name = "Help") {
                navController.navigate(AppGraph.detailsHome.FAQ)
            }
        }
        composable(route = AppGraph.detailsHome.FAQ) {
            ViewContent(name = "Faq") {
                navController.popBackStack(
                    route = AppGraph.detailsHome.FAQ,
                    inclusive = false
                )
            }
        }
    }
}