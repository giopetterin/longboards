package com.example.longboardapp.view.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ShoppingCart
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition",
    "CheckResult", "SuspiciousIndentation"
)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DancingLongBoard(navController: NavController) {

    val longBoards: List<LongBoardModel> = getAllLongBoardsFromDB()

    var varLocalLB = LongBoardModel("","", 0.0)

        longBoards.map { l ->  if (l.tittle == "Dancing")  varLocalLB = l }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                ),
                actions = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 2.dp, top = 8.dp),
                        contentAlignment = Alignment.TopStart
                    ) {
                        IconButton(onClick = {
                            navController.navigate(route = AppScreens.MenuLongBoards.route)
                        })
                        {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Volver al menu"
                            )
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 2.dp, top = 8.dp, end= 2.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("Bienvenido a los Dancing Long Board!!",
                                color= MaterialTheme.colorScheme.onPrimary)
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 2.dp, top = 8.dp),
                            contentAlignment = Alignment.TopEnd
                        ) {
                        IconButton(
                            onClick = {
                            navController.navigate(route = AppScreens.CarritoLongBoards.route)
                                      }
                                  ){
                                     Icon(
                                        imageVector = Icons.Filled.ShoppingCart,
                                        contentDescription = "Carrito compras"
                                        )
                                 }
                            }
                    }
                },
                title = {
                    Text("Bienvenido a los Dancing Long Board")
                }
            )
        }
    ) {
        DancingBodyContent(varLocalLB)
    }
}

@Composable
fun ImageDancing() {
    Image(
        painterResource(R.drawable.imagedancinglongboard),
        "Mi imagen dancing",
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary)
            .height(200.dp)

    )
}


@Composable
fun DancingBodyContent(longBoard: LongBoardModel) {
    Box(
        modifier = Modifier
            .padding(start = 2.dp, 86.dp)
            .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(5.dp))
            .height(400.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ImageDancing()
            MyDualTextsExpanded(
                longBoard.tittle,
                longBoard.body
            )
            Row (  modifier = Modifier.fillMaxSize().padding(2.dp) ){

                Text(text = longBoard.price.toString(),
                    modifier = Modifier.padding(2.dp, 10.dp),
                    color = MaterialTheme.colorScheme.primary,
                   style =  MaterialTheme.typography.titleMedium)
                IconButton(modifier = Modifier.padding(1.dp, 1.dp),
                    onClick = {
                        carritoLongBoards.add(
                            longBoard
                        )
                    }
                )
                {
                    Icon (
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Agregar al carrito"
                    )

                }

            }

        }
    }

}
