package com.example.longboardapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.longboardapp.view.screens.BalanceLongBoard
import com.example.longboardapp.view.screens.CarritoLongBoard
import com.example.longboardapp.view.screens.CruisingLongBoard
import com.example.longboardapp.view.screens.DancingLongBoard
import com.example.longboardapp.view.screens.MenuLongBoard
import com.example.longboardapp.view.screens.SurfTStakeLongBoard


/**
 * Se va encargar de orquestar nuestra navegacion debemos agregar una libreria.
 */


@Composable
fun AppNavigation() {
    val navController =  rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.MenuLongBoards.route) {
        composable(route = AppScreens.MenuLongBoards.route) {
           MenuLongBoard(navController)
        }
        composable(route = AppScreens.DancingLongBoards.route) {
            DancingLongBoard(navController)
        }
        composable(route = AppScreens.CarritoLongBoards.route) {
            CarritoLongBoard(navController)
        }
        composable(route = AppScreens.CruisingLongBoards.route) {
          CruisingLongBoard(navController)
        }
        composable(route = AppScreens.SurfSTakeLongBoards.route) {
            SurfTStakeLongBoard(navController)
        }
        composable(route = AppScreens.BalanceLongBoards.route) {
            BalanceLongBoard(navController)
        }
    }
}

fun myRouteLongBoards(name: String, navController: NavController) {
    if (name == "Dancing")
        navController.navigate(
            route = AppScreens.DancingLongBoards.route
        )
    else if (name == "Cruising")
        navController.navigate(
            route = AppScreens.CruisingLongBoards.route
        )
    else if (name == "SurfTStake")
        navController.navigate(
            route = AppScreens.SurfSTakeLongBoards.route
        )
    else if (name == "Balance")
        navController.navigate(
            route = AppScreens.BalanceLongBoards.route
        )
}