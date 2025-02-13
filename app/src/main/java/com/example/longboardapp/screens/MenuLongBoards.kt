package com.example.longboardapp.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.longboardapp.components.MiniMenuOptions
import com.example.longboardapp.components.MyLongBoards
import com.example.longboardapp.navigation.myRouteLongBoards

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuLongBoard(navController: NavController) {

    Scaffold( containerColor = MaterialTheme.colorScheme.errorContainer,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Bienvenido a  Geo  Long Board")
                },
                actions = {
                    var isMenuOpened by remember { mutableStateOf(false) }
                  IconButton(onClick = {isMenuOpened = true}) {
                      Icon(imageVector = Icons.Filled.MoreVert,
                          contentDescription = "Opciones de menu")
                      MiniMenuOptions(isExpanded = isMenuOpened,
                          onItemClick = {
                              item ->
                              myRouteLongBoards(item, navController)
                              Log.i("TAG", "Elemento :  $item")
                          }) {
                          isMenuOpened = false
                      }
                  }
                }
            )
        }
    ){
        BodyContent(navController)
    }
}

@Composable
fun BodyContent(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MyLongBoards(navController)
    }
}

