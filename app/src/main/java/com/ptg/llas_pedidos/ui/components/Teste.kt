package com.ptg.llas_pedidos.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
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
    //Objetos da de navegacao do Master
    object MenuM : BottomBarConfigItem(
        route = AppGraph.master.MENU,
        title = "Menu",
        icon = Icons.Default.Menu
    )

    object OrderM : BottomBarConfigItem(
        route = AppGraph.master.ORDER,
        title = "Pedido",
        icon = Icons.Default.ShoppingCart
    )

    object SupportM : BottomBarConfigItem(
        route = AppGraph.master.SUPPORT,
        title = "Suporte",
        icon = Icons.Default.Info
    )


    //Objetos de navegação do Cliente
    object HomeC : BottomBarConfigItem(
        route = AppGraph.client.HOME,
        title = "Home",
        icon = Icons.Default.Home
    )
    object MenuC : BottomBarConfigItem(
        route = AppGraph.client.MENU,
        title = "Menu",
        icon = Icons.Default.Menu
    )

    object OrderC : BottomBarConfigItem(
        route = AppGraph.client.ORDER,
        title = "Pedido",
        icon = Icons.Default.ShoppingCart
    )

    object SupportC : BottomBarConfigItem(
        route = AppGraph.client.SUPPORT,
        title = "Suporte",
        icon = Icons.Default.Info
    )
}

//Lista de todas as configurações de rotas da barra de navegação
object BottomBarConfigLis {

    val master = listOf(
        BottomBarConfigItem.MenuM,
        BottomBarConfigItem.OrderM,
        BottomBarConfigItem.SupportM
    )

    val client = listOf(
        BottomBarConfigItem.HomeC,
        BottomBarConfigItem.MenuC,
        BottomBarConfigItem.OrderC,
        BottomBarConfigItem.SupportC
    )
}

@Composable
fun BottomBar(navController: NavController) {
    // Identifica a  rota de destino no momento
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val master = true

    if (master) {
        val showNavigationItemsM = BottomBarConfigLis.master.any { it.route == currentDestination?.route }
        if (showNavigationItemsM) {
            BottomNavigation {
                // Exibe os itens na barra de navegação
                BottomBarConfigLis.master.forEach { itemConfig ->
                    AddItem(
                        itemConfig = itemConfig,
                        currentDestination = currentDestination,
                        navController = navController
                    )
                }
            }
        }
    } else {
        val showNavigationItemsC = BottomBarConfigLis.client.any { it.route == currentDestination?.route }
        if (showNavigationItemsC) {
            BottomNavigation {
                // Exibe os itens na barra de navegação
                BottomBarConfigLis.client.forEach { itemConfig ->
                    AddItem(
                        itemConfig = itemConfig,
                        currentDestination = currentDestination,
                        navController = navController
                    )
                }
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