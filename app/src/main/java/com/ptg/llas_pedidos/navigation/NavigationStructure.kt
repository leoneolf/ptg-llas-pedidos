package com.ptg.llas_pedidos.navigation

/*
*  Definindo:
*   - a estrutura de navegação
 */

//Root inicial, quando abre o app
object AuthGraph {
    const val ROOT = "auth_graph"
//    const val LOGIN = "login"
    const val AUTH = "auth"
    const val OPTIONS = "options"
}

//Root e telas do Master para fazer a navegação
object MasterGraph {
    const val ROOT = "master_graph"
    const val MENU = "menu"
    const val ORDER = "order"
    const val SUPPORT = "support"
}

object MasterMenuGraph {
    const val ROOT = "master_menu_graph"
    const val ITEM = "item"
    const val ADD = "add"
    const val EDIT = "edit"
}

object MasterOrderGraph {
    const val ROOT = "master_order_graph"
    const val DETAILS = "details"
}

object MasterSupportGraph {
    const val ROOT = "master_support_graph"
    const val DETAILS = "details"
}

//Root e telas do Cliente para fazer a navegação
object ClientGraph {
    const val ROOT = "client_graph"
    const val HOME = "home"
    const val MENU = "menu"
    const val ORDER = "order"
    const val SUPPORT = "support"
}

object ClientMenuGraph {
    const val ROOT = "client_menu_graph"
    const val ADD = "add"
    const val CART = "cart"
}

object ClientOrderGraph {
    const val ROOT = "client_order_graph"
    const val EDIT = "edit"
}

object ClientSupportGraph {
    const val ROOT = "client_support_graph"
    const val ADD = "add"
}

// Objetos que fazem o direcionamento para cada tela, a partir da inicial
object RootGraph {
    const val ROOT = "root_graph"
}

object AppGraph {
    //Roots iniciais
    val auth = AuthGraph
    val initial = RootGraph

    //Root master e afins
    val master = MasterGraph
    val masterMenu = MasterMenuGraph
    val masterOrder = MasterOrderGraph
    val masterSupport = MasterSupportGraph

    //Root cliente e afins
    val client = ClientGraph
    val clientMenu = ClientMenuGraph
    val clientOrder = ClientOrderGraph
    val clientSupport = ClientSupportGraph
}


