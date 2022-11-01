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

// Congigurando os itens na barra de navegação do Cliente
sealed class ClientBottomBarConfigItem(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object  Home : ClientBottomBarConfigItem(
        route = AppGraph.client.HOME,
        title = "Home",
        icon = Icons.Default.Home
    )

    object Menu : ClientBottomBarConfigItem(
        route = AppGraph.client.MENU,
        title = "Menu",
        icon = Icons.Default.Menu
    )

    object Order : ClientBottomBarConfigItem(
        route = AppGraph.client.ORDER,
        title = "Pedido",
        icon = Icons.Default.ShoppingCart
    )

    object Support : ClientBottomBarConfigItem(
        route = AppGraph.client.SUPPORT,
        title = "Suporte",
        icon = Icons.Default.Info
    )
}

//Lista de todas as configurações de rotas da barra de navegação do cliente
object ClientBottomBarConfigLis {
    val all = listOf(
        ClientBottomBarConfigItem.Home,
        ClientBottomBarConfigItem.Menu,
        ClientBottomBarConfigItem.Order,
        ClientBottomBarConfigItem.Support
    )
}


@Composable
fun BottomBarClient(navController: NavController) {
    // Identifica a  rota de destino no momento
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val showClientNavigationItems = ClientBottomBarConfigLis.all.any { it.route == currentDestination?.route }

    if (showClientNavigationItems) {
        BottomNavigation {
            // Exibe os itens na barra de navegação
            ClientBottomBarConfigLis.all.forEach { itemConfigClient ->
                AddItemClient(
                    itemConfigClient = itemConfigClient,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun RowScope.AddItemClient(
    itemConfigClient: ClientBottomBarConfigItem,
    currentDestination: NavDestination?,
    navController: NavController
) {
    BottomNavigationItem(
        label = {
            Text(text = itemConfigClient.title)
        },
        icon = {
            Icon(
                imageVector = itemConfigClient.icon,
                contentDescription = "Navigation Icon"
            )
        },
        selected = currentDestination?.hierarchy?.any { it.route == itemConfigClient.route } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(itemConfigClient.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}

