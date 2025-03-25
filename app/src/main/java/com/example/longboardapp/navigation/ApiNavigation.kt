package com.example.longboardapp.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.longboardapp.view.screens.ABMLongBoards
import com.example.longboardapp.view.screens.AltaProductoForm
import com.example.longboardapp.view.screens.BalanceLongBoard
import com.example.longboardapp.view.screens.CarritoLongBoard
import com.example.longboardapp.view.screens.CruisingLongBoard
import com.example.longboardapp.view.screens.DancingLongBoard
import com.example.longboardapp.view.screens.ListaProductos
import com.example.longboardapp.view.screens.LoginLongBoards
import com.example.longboardapp.view.screens.MenuLongBoard
import com.example.longboardapp.view.screens.MenuPrincipal
import com.example.longboardapp.view.screens.SurfTStakeLongBoard


/**
 * Se va encargar de orquestar nuestra navegacion debemos agregar una libreria.
 */


@SuppressLint("CheckResult")
@Composable
fun AppNavigation() {


    val navController =  rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.MenuPrincipal.route) {

        composable(route = AppScreens.MenuPrincipal.route) {
            MenuPrincipal(navController)
        }
        composable(route = AppScreens.MenuLongBoards.route) {
            MenuLongBoard(navController)
        }
        composable(route = AppScreens.ABMLongBoards.route) {
            ABMLongBoards(navController)
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
        composable(route = AppScreens.LoginScreen.route) {
            LoginLongBoards(navController)
        }
    }
}

fun myRouteLongBoards(name: String, navController: NavController) {
    when (name) {
        "Dancing" -> navController.navigate(
            route = AppScreens.DancingLongBoards.route
        )
        "Cruising" -> navController.navigate(
            route = AppScreens.CruisingLongBoards.route
        )
        "SurfTStake" -> navController.navigate(
            route = AppScreens.SurfSTakeLongBoards.route
        )
        "Balance" -> navController.navigate(
            route = AppScreens.BalanceLongBoards.route
        )
        "Menu" -> navController.navigate(
            route = AppScreens.MenuLongBoards.route
        )
    }
}