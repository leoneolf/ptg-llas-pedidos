package com.ptg.llas_pedidos.navigation

/*
*  Definindo:
*   - a estrutura de navegação
 */

object AuthGraph {
    const val ROOT = "auth_graph"
    const val LOGIN = "login"
    const val SING_UP = "sing_up"
    const val SING_OUT = "sing_out"
}

object MainGraph {
    const val ROOT = "main_graph"
    const val HOME = "home"
    const val MENU = "menu"
    const val ORDER = "order"
    const val SUPPORT = "support"
    const val CLIENT = "client"
}

object DetailsHomeGraph {
    const val ROOT = "details_home_graph"
    const val HELP = "help"
    const val FAQ = "faq"
}

object RootGraph {
    const val ROOT = "root_graph"
}

object AppGraph {
    val auth = AuthGraph
    val main = MainGraph
    val detailsHome = DetailsHomeGraph
    val initial = RootGraph
}


//object ClientGraph {
//    const val ROOT = "client_graph"
//    const val HOME = "client_home"
//    const val MENU = "client_menu"
//    const val ORDER = "client_order"
//    const val SUPPORT = "client_support"
//}

