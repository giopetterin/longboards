package com.example.longboardapp.view.screens

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
import com.example.longboardapp.components.MyDualTextsExpanded
import com.example.longboardapp.components.getAllLongBoardsFromDB
import com.example.longboardapp.model.LongBoardModel
import com.example.longboardapp.navigation.AppScreens

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CruisingLongBoard(navController: NavController) {

    val longBoards: List<LongBoardModel> = getAllLongBoardsFromDB()

    var varLocalLB = LongBoardModel("","", 0.0)

    longBoards.map { l ->  if (l.tittle == "Cruising")  varLocalLB = l }

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
                    Text("Bienvenido a los Cruising Long Board")
                }
            )
        }
    ){
        CruisingBodyContent(varLocalLB)
    }
}

@Composable
fun ImageCruising(){
    Image(
        painterResource(R.drawable.imagecruisinglongboard),
        "Mi imagen cruising",
        modifier = Modifier
            .size(400.dp)
            .background(MaterialTheme.colorScheme.primary)

    )
}

@Composable
fun CruisingBodyContent(longBoard: LongBoardModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MyDualTextsExpanded(
            longBoard.tittle ,
            longBoard.body)
        ImageCruising()
    }
}
