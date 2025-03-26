package com.example.longboardapp.view.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.example.longboardapp.components.MiniMenuOptions
import com.example.longboardapp.components.MyLongBoards
import com.example.longboardapp.components.MyText
import com.example.longboardapp.components.getAllLongBoardsFromDB
import com.example.longboardapp.model.LongBoardModel
import com.example.longboardapp.navigation.myRouteLongBoards


var carritoLongBoards: ArrayList<LongBoardModel> = ArrayList()

@SuppressLint(
    "UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition",
    "SuspiciousIndentation", "CheckResult"
)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuLongBoard(navController: NavController) {

    val longBoards: List<LongBoardModel> = getAllLongBoardsFromDB()


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                title = {
                    MyText(
                        "Welcome LongBoards kind",
                        MaterialTheme.colorScheme.onPrimary,
                        MaterialTheme.typography.titleLarge,
                        Int.MAX_VALUE,
                        TextAlign.Center
                    )
                },
                actions = {
                    var isMenuOpened by remember { mutableStateOf(false) }
                    IconButton(onClick = { isMenuOpened = true }) {
                        Icon(
                            imageVector = Icons.Filled.MoreVert,
                            contentDescription = "Opciones de menu"
                        )

                        MiniMenuOptions(
                            longBoards, isExpanded = isMenuOpened,
                            onItemClick = { item ->
                                myRouteLongBoards(item, navController)
                                Log.i("TAG", "Elemento :  $item")
                            }) {
                            isMenuOpened = false
                        }
                    }
                }
            )
        }
    ) {
        if (!longBoards.any()) {
            RefreshButtonExample(navController)
        } else BodyContent(navController, longBoards)
    }
}


@Composable
fun BodyContent(navController: NavController, longBoards: List<LongBoardModel>) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        MyLongBoards(longBoards, navController)
    }
}


@Composable
fun RefreshButtonExample(navController: NavController) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextButton(
            onClick = { myRouteLongBoards("Menu", navController) }
        ) {
            Icon(
                imageVector = Icons.Filled.Refresh,
                contentDescription = "Refresh"
            )
        }
    }
}

