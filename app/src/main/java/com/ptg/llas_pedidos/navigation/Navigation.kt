package com.ptg.llas_pedidos.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.ptg.llas_pedidos.MainActivity
import com.ptg.llas_pedidos.data.CreateNewItem
import com.ptg.llas_pedidos.ui.components.AdminSingIn
import com.ptg.llas_pedidos.ui.components.AuthenticationButton
import com.ptg.llas_pedidos.ui.components.ClientHomeScreen
import com.ptg.llas_pedidos.ui.components.SingInButton

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
        startDestination = AppGraph.auth.OPTIONS
    ) {
        composable(route = AppGraph.auth.OPTIONS) {
            AuthenticationButton(
                singInAdminCliked = {
                    navController.popBackStack(
                        route = AppGraph.auth.OPTIONS,
                        inclusive = false
                    )
                    navController.navigate(AppGraph.auth.AUTH)
                },
                singInClientCliked = {
                    navController.navigate(AppGraph.client.ROOT)
                }
            )
        }
        composable(AppGraph.auth.AUTH) {
            AdminSingIn {
                navController.navigate(AppGraph.master.ROOT)
            }
        }
    }
}

@Composable
fun MasterNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = AppGraph.master.ROOT,
        startDestination = AppGraph.master.MENU
    ) {
        composable(route = AppGraph.master.MENU) {
            CreateNewItem()
        }
        composable(route = AppGraph.master.ORDER) {
            ViewContent(
                name = "Pedido",
                onClick = {
                    navController.navigate(AppGraph.masterOrder.ROOT)
                }
            )
        }
        composable(route = AppGraph.master.SUPPORT) {
            ViewContent(
                name = "Suporte",
                onClick = {
                    navController.navigate(AppGraph.masterSupport.ROOT)
                }
            )
        }

        masterMenuNavGraph(navController = navController)
        masterOrderNavGraph(navController = navController)
        masterSupportNavGraph(navController = navController)
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
            //Pegando a instanciado do user conectado no momento
            val mAuth = FirebaseAuth.getInstance()
            val user: FirebaseUser = mAuth.currentUser!!

            ClientHomeScreen(
                profileImage = user.photoUrl!!,
                name = user.displayName!!,
                email = user.email!!
            )
        }
        composable(route = AppGraph.client.MENU) {
            ViewContent(
                name = "Menu",
                onClick = {
                    navController.navigate(AppGraph.clientMenu.ROOT)
                }
            )
        }
        composable(route = AppGraph.client.ORDER) {
            ViewContent(
                name = "Pedido",
                onClick = {
                    navController.navigate(AppGraph.clientOrder.ROOT)
                }
            )
        }
        composable(route = AppGraph.client.SUPPORT) {
            ViewContent(
                name = "Suporte",
                onClick = {
                    navController.navigate(AppGraph.clientSupport.ROOT)
                }
            )
        }

        clientMenuNavGraph(navController = navController)
        clientOrderNavGraph(navController = navController)
        clientSupportNavGraph(navController = navController)
    }
}

fun NavGraphBuilder.masterMenuNavGraph(navController: NavController) {
    navigation(
        route = AppGraph.masterMenu.ROOT,
        startDestination = AppGraph.masterMenu.ITEM
    ) {
        composable(AppGraph.masterMenu.ITEM) {
            ViewContent(
                name = "Item",
                onClick = {
                    navController.navigate(AppGraph.masterMenu.EDIT)
                }
            )
        }
        composable(AppGraph.masterMenu.EDIT) {
            ViewContent(
                name = "Editar item",
                onClick = {
                    navController.popBackStack(
                        route = AppGraph.masterMenu.EDIT,
                        inclusive = false
                    )
                }
            )
        }
        composable(AppGraph.masterMenu.ADD) {
            ViewContent(
                name = "Adicionar item",
                onClick = {
                    navController.popBackStack(
                        route = AppGraph.masterMenu.ADD,
                        inclusive = false
                    )
                }
            )
        }
    }
}

fun NavGraphBuilder.masterOrderNavGraph(navController: NavController) {
    navigation(
        route = AppGraph.masterOrder.ROOT,
        startDestination = AppGraph.masterOrder.DETAILS
    ) {
        composable(AppGraph.masterOrder.DETAILS) {
            ViewContent(
                name = "Detalhes Pedido",
                onClick = {
                    navController.popBackStack(
                        route = AppGraph.masterOrder.DETAILS,
                        inclusive = false
                    )
                }
            )
        }
    }
}

fun NavGraphBuilder.masterSupportNavGraph(navController: NavController) {
    navigation(
        route = AppGraph.masterSupport.ROOT,
        startDestination = AppGraph.masterSupport.DETAILS
    ) {
        composable(AppGraph.masterSupport.DETAILS) {
            ViewContent(
                name = "Detalhes Suporte",
                onClick = {
                    navController.popBackStack(
                        route = AppGraph.masterSupport.DETAILS,
                        inclusive = false
                    )
                }
            )
        }
    }
}

fun NavGraphBuilder.clientMenuNavGraph(navController: NavController) {
    navigation(
        route = AppGraph.clientMenu.ROOT,
        startDestination = AppGraph.clientMenu.ADD
    ) {
        composable(AppGraph.clientMenu.ADD) {
            ViewContent(
                name = "Adicinar ao carrinho",
                onClick = {
                    navController.navigate(AppGraph.clientMenu.CART)
                }
            )
        }
        composable(AppGraph.clientMenu.CART) {
            ViewContent(
                name = "Carrinho",
                onClick = {
                    navController.popBackStack(
                        route = AppGraph.clientMenu.CART,
                        inclusive = false
                    )
                }
            )
        }

    }
}

fun NavGraphBuilder.clientOrderNavGraph(navController: NavController) {
    navigation(
        route = AppGraph.clientOrder.ROOT,
        startDestination = AppGraph.clientOrder.EDIT
    ) {
        composable(AppGraph.clientOrder.EDIT) {
            ViewContent(
                name = "Editar pedido",
                onClick = {
                    navController.popBackStack(
                        route = AppGraph.clientOrder.EDIT,
                        inclusive = false
                    )
                }
            )
        }
    }
}

fun NavGraphBuilder.clientSupportNavGraph(navController: NavController) {
    navigation(
        route = AppGraph.clientSupport.ROOT,
        startDestination = AppGraph.clientSupport.ADD
    ) {
        composable(AppGraph.clientSupport.ADD) {
            ViewContent(
                name = "Abrir um chamado",
                onClick = {
                    navController.popBackStack(
                        route = AppGraph.clientSupport.ADD,
                        inclusive = false
                    )
                }
            )
        }
    }
}
