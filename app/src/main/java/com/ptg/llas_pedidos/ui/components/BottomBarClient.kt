//package com.ptg.llas_pedidos.ui.components
//
//import androidx.compose.foundation.layout.RowScope
//import androidx.compose.material.*
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.*
//import androidx.compose.material3.Icon
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.ui.graphics.vector.ImageVector
//import androidx.navigation.NavController
//import androidx.navigation.NavDestination
//import androidx.navigation.NavDestination.Companion.hierarchy
//import androidx.navigation.NavGraph.Companion.findStartDestination
//import androidx.navigation.compose.currentBackStackEntryAsState
//import com.ptg.llas_pedidos.navigation.AppGraph
//
///*
//*  Definindo:
//*   - bottom bar/ navigation bar:
//*/
//
//// Congigurando os itens na barra de navegação do Cliente
//sealed class ClientBottomBarConfigItem(
//    val routes: String,
//    val titles: String,
//    val icons: ImageVector
//) {
//    object  Home : ClientBottomBarConfigItem(
//        routes = AppGraph.client.HOME,
//        titles = "Home",
//        icons = Icons.Default.Home
//    )
//
//    object Menu : ClientBottomBarConfigItem(
//        routes = AppGraph.client.MENU,
//        titles = "Menu",
//        icons = Icons.Default.Menu
//    )
//
//    object Order : ClientBottomBarConfigItem(
//        routes = AppGraph.client.ORDER,
//        titles = "Pedido",
//        icons = Icons.Default.ShoppingCart
//    )
//
//    object Support : ClientBottomBarConfigItem(
//        routes = AppGraph.client.SUPPORT,
//        titles = "Suporte",
//        icons = Icons.Default.Info
//    )
//}
//
////Lista de todas as configurações de rotas da barra de navegação do cliente
//object ClientBottomBarConfigLis {
//    val allC = listOf(
//        ClientBottomBarConfigItem.Home,
//        ClientBottomBarConfigItem.Menu,
//        ClientBottomBarConfigItem.Order,
//        ClientBottomBarConfigItem.Support
//    )
//}
//
//
//@Composable
//fun BottomBarClient(navController: NavController) {
//    // Identifica a  rota de destino no momento
//    val navBackStackEntry by navController.currentBackStackEntryAsState()
//    val currentDestination = navBackStackEntry?.destination
//
//    val showClientNavigationItems = ClientBottomBarConfigLis.allC.any { it.routes == currentDestination?.route }
//
//    if (showClientNavigationItems) {
//        BottomNavigation {
//            // Exibe os itens na barra de navegação
//            ClientBottomBarConfigLis.allC.forEach { itemConfigClient ->
//                AddItemClient(
//                    itemConfigClient = itemConfigClient,
//                    currentDestination = currentDestination,
//                    navController = navController
//                )
//            }
//        }
//    }
//}
//
//
//
//@Composable
//fun RowScope.AddItemClient(
//    itemConfigClient: ClientBottomBarConfigLis,
//    currentDestination: NavDestination?,
//    navController: NavController
//) {
//    BottomNavigationItem(
//        label = {
//            Text(text = itemConfigClient.titles)
//        },
//        icon = {
//            Icon(
//                imageVector = itemConfigClient.icons,
//                contentDescription = "Navigation Icon"
//            )
//        },
//        selected = currentDestination?.hierarchy?.any { it.route == itemConfigClient.routes } == true,
//        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
//        onClick = {
//            navController.navigate(itemConfigClient.routes) {
//                popUpTo(navController.graph.findStartDestination().id)
//                var launchSingleTop = true
//            }
//        }
//    )
//}
//
