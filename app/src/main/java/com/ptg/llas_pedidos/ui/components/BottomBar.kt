package com.ptg.llas_pedidos.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ptg.llas_pedidos.navigation.AppGraph

/*
*  Definindo:
*   - bottom bar/ navigation bar:
*/

// Congigurando os itens na barra de navegação
sealed class BottomBarConfigItem(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomBarConfigItem(
        route = AppGraph.main.HOME,
        title = "Home",
        icon = Icons.Default.Home
    )

    object Menu : BottomBarConfigItem(
        route = AppGraph.main.MENU,
        title = "Cardápio",
        icon = Icons.Default.Menu
    )

    object Order : BottomBarConfigItem(
        route = AppGraph.main.ORDER,
        title = "Pedido",
        icon = Icons.Default.ShoppingCart
    )

    object Support : BottomBarConfigItem(
        route = AppGraph.main.SUPPORT,
        title = "Suporte",
        icon = Icons.Default.Info
    )

    object Client : BottomBarConfigItem(
        route = AppGraph.main.CLIENT,
        title = "Cliente",
        icon = Icons.Default.Person
    )
}

//Lista de todas as configurações de rotas da barra de navegação
object BottomBarConfigLis {
    val all = listOf(
        BottomBarConfigItem.Home,
        BottomBarConfigItem.Menu,
        BottomBarConfigItem.Order,
        BottomBarConfigItem.Support,
        BottomBarConfigItem.Client
    )
}

@Composable
fun BottomBar(navController: NavController) {
    // Identifica a  rota de destino no momento
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val showNavigationItems = BottomBarConfigLis.all.any { it.route == currentDestination?.route }
    if (showNavigationItems) {
        BottomNavigation {
            // Exibe os itens na barra de navegação
            BottomBarConfigLis.all.forEach { itemConfig ->
                AddItem(
                    itemConfig = itemConfig,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    itemConfig: BottomBarConfigItem,
    currentDestination: NavDestination?,
    navController: NavController
) {
    BottomNavigationItem(
        label = {
            Text(text = itemConfig.title)
        },
        icon = {
            Icon(
                imageVector = itemConfig.icon,
                contentDescription = "Navigation Icon"
            )
        },
        selected = currentDestination?.hierarchy?.any { it.route == itemConfig.route } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(itemConfig.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}