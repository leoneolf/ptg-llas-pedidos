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

// Congigurando os itens na barra de navegação do Master
sealed class MasterBottomBarConfigItem(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Menu : MasterBottomBarConfigItem(
        route = AppGraph.master.MENU,
        title = "Menu",
        icon = Icons.Default.Menu
    )

    object Order : MasterBottomBarConfigItem(
        route = AppGraph.master.ORDER,
        title = "Pedido",
        icon = Icons.Default.ShoppingCart
    )

    object Support : MasterBottomBarConfigItem(
        route = AppGraph.master.SUPPORT,
        title = "Suporte",
        icon = Icons.Default.Info
    )
}

//Lista de todas as configurações de rotas da barra de navegação do master
object MasterBottomBarConfigLis {
    val all = listOf(
        MasterBottomBarConfigItem.Menu,
        MasterBottomBarConfigItem.Order,
        MasterBottomBarConfigItem.Support
    )
}

@Composable
fun BottomBarMaster(navController: NavController) {
    // Identifica a  rota de destino no momento
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val showMasterNavigationItems = MasterBottomBarConfigLis.all.any { it.route == currentDestination?.route }

    if (showMasterNavigationItems) {
        BottomNavigation {
            // Exibe os itens na barra de navegação
            MasterBottomBarConfigLis.all.forEach { itemConfigMaster ->
                AddItemMaster(
                    itemConfigMaster = itemConfigMaster,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun RowScope.AddItemMaster(
    itemConfigMaster: MasterBottomBarConfigItem,
    currentDestination: NavDestination?,
    navController: NavController
) {
    BottomNavigationItem(
        label = {
            Text(text = itemConfigMaster.title)
        },
        icon = {
            Icon(
                imageVector = itemConfigMaster.icon,
                contentDescription = "Navigation Icon"
            )
        },
        selected = currentDestination?.hierarchy?.any { it.route == itemConfigMaster.route } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(itemConfigMaster.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}
