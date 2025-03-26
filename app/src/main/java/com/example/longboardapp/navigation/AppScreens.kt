package com.example.longboardapp.navigation

/**
 * Para indicar cuales son las pantallas a las que podemos navegar.
 */
sealed class AppScreens (val route: String) {
    data object MenuLongBoards: AppScreens("menu_longBoards")
    data object DancingLongBoards: AppScreens("dancing_longBoards")
    data object CarritoLongBoards: AppScreens("carrito_longBoards")
    data object CruisingLongBoards: AppScreens("cruising_longBoards")
    data object SurfSTakeLongBoards: AppScreens("surfStake_longBoards")
    data object BalanceLongBoards: AppScreens("balance_longBoards")
    data object LoginScreen: AppScreens("login_longBoards")
    data object ABMLongBoards: AppScreens("abm_longboards")
    data object MenuPrincipal: AppScreens("menu_principal")
    data object AltaProductoForm: AppScreens("alta_producto_form")
}