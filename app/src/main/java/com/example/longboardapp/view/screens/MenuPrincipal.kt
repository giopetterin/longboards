package com.example.longboardapp.view.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.longboardapp.navigation.AppScreens
import java.lang.reflect.Modifier


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MenuPrincipal(navController: NavController) {

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                ),
                actions = {
                    Box(
                        modifier = androidx.compose.ui.Modifier
                            .fillMaxWidth()
                            .padding(start = 2.dp, top = 8.dp),
                        contentAlignment = Alignment.TopStart
                    ) {
                        Box(
                            modifier = androidx.compose.ui.Modifier
                                .fillMaxWidth()
                                .padding(start = 2.dp, top = 8.dp, end= 2.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("Menu Principal",
                                color= MaterialTheme.colorScheme.onPrimary)
                        }
                    }
                },
                title = {
                    Text("Menu Principal")
                }
            )
        }
    ) {
        PantallaPrincipal(navController)
    }
}

@Composable
fun PantallaPrincipal(navController: NavController) {
    Column(
        modifier =  androidx.compose.ui.Modifier
            .fillMaxWidth()
            .padding(start = 2.dp, top = 200.dp, end= 2.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {navController.navigate(route = AppScreens.MenuLongBoards.route) }) {
            Text("Ir a Menu Long Boards")
        }
        Button(onClick = { navController.navigate(route = AppScreens.ABMLongBoards.route)}) {
            Text("Ir a ABM Long Boards")
        }
        Button(onClick = { navController.navigate(route = AppScreens.LoginScreen.route)}) {
            Text("Ir a Login")
        }
    }
}