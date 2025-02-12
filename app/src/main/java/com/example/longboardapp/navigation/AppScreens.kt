package com.example.longboardapp.navigation

/**
 * Para indicar cuales son las pantallas a las que podemos navegar.
 */
sealed class AppScreens (val route: String) {
    object MenuLongBoards: AppScreens("menu_longBoards")
    object DancingLongBoards: AppScreens("dancing_longBoards")
    object CruisingLongBoards: AppScreens("cruising_longBoards")
    object SurfSTakeLongBoards: AppScreens("surfStake_longBoards")
    object BalanceLongBoards: AppScreens("balance_longBoards")
}