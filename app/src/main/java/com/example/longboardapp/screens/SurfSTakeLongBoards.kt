package com.example.longboardapp.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.longboardapp.R
import com.example.longboardapp.components.MyTexts
import com.example.longboardapp.components.longBoards
import com.example.longboardapp.navigation.AppScreens

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SurfTStakeLongBoard(navController: NavController) {

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                actions = {
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 2.dp, top = 8.dp),
                        contentAlignment = Alignment.TopStart
                    ){
                        IconButton(onClick = {
                            navController.navigate(route = AppScreens.MenuLongBoards.route)})
                        {
                            Icon(imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Volver al menu")

                        }
                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 2.dp, top = 8.dp),
                            contentAlignment = Alignment.Center )
                        {
                            Text("Bienvenido a los Dancing Long Board!!")
                        }
                    }
                },
                title = {
                    Text("Bienvenido a los SurfTStake Long Board")
                }
            )
        }
    ){
        SurfTStakeBodyContent()
    }
}


@Composable
fun ImageSurfTStake(){
    Image(
        painterResource(R.drawable.imagesurfskate),
        "Mi imagen surfTStake",
        modifier = Modifier
            .size(400.dp)
            .background(MaterialTheme.colorScheme.onBackground)
    )
}

@Composable
fun SurfTStakeBodyContent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MyTexts(
            longBoards.asIterable().first { it.tittle == "SurfTStake" }.tittle ,
            longBoards.asIterable().first { it.tittle == "SurfTStake" }.body)
        ImageSurfTStake()
    }
}