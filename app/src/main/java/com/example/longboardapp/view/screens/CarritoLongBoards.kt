package com.example.longboardapp.view.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.longboardapp.components.MyImageLongBoards
import com.example.longboardapp.model.LongBoardModel
import com.example.longboardapp.navigation.AppScreens


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CarritoLongBoard(navController: NavController) {

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
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
                            Text("Carrito de Compras",
                                color= MaterialTheme.colorScheme.onPrimary)
                        }
                    }
                },
                title = {
                    Text("Carrito de compras")
                }
            )
        }
    ) {
        MyCarritoLongBoards(carritoLongBoards, navController)
    }
}


@Composable
fun MyCarritoLongBoards(carritoLongBoards: List<LongBoardModel>, navController: NavController) {
    var total: Double = 0.0
    val longBoards: Set<LongBoardModel> =  carritoLongBoards.toSet()

     longBoards.forEach { lSet ->
         total += carritoLongBoards.count { it.tittle == lSet.tittle } * lSet.price
     }

    Box(
        modifier = Modifier
            .padding(start = 1.dp, top = 100.dp, end = 1.dp)
            .height(700.dp),
        contentAlignment = Alignment.TopStart
    ){
        Column {
            longBoards.toList().forEach{ longBoard ->
                MyBodyCarritoContent(longBoard, navController)
            }

            Box(
                modifier = Modifier
                    .padding(start = 1.dp, top = 10.dp, end = 1.dp),
                contentAlignment = Alignment.TopEnd
            ){
                Text(
                    text = "Total = $total",
                    modifier = Modifier.padding(18.dp, 5.dp),
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Justify
                )
            }

        }
    }




}



@Composable
fun MyBodyCarritoContent(longBoard: LongBoardModel, navController: NavController) {
    Box(
        modifier = Modifier
            .padding(start = 10.dp, top = 10.dp, end = 10.dp)
            .border(1.dp, MaterialTheme.colorScheme.onPrimary, RoundedCornerShape(5.dp))
            .height(150.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        Row() {
            MyImageLongBoards(longBoard.tittle)

            Text(
                text = longBoard.tittle,
                modifier = Modifier.padding(2.dp, 50.dp),
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Justify
            )
            Column() {
                IconButton(modifier = Modifier.padding(top = 10.dp),
                    onClick = {
                        carritoLongBoards.add(longBoard)
                        navController.navigate(route = AppScreens.CarritoLongBoards.route)
                    }
                )
                {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowUp,
                        contentDescription = "Agregar al carrito"
                    )

                }
                Text(
                    text = carritoLongBoards.count { it.tittle == longBoard.tittle }.toString(),
                    modifier = Modifier.padding(18.dp, 5.dp),
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Justify
                )
                IconButton(
                    onClick = {
                        carritoLongBoards.remove(longBoard)
                        navController.navigate(route = AppScreens.CarritoLongBoards.route)
                    }
                )
                {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = "Agregar al carrito"
                    )

                }
            }

            Text(
                text = longBoard.price.toString(),
                modifier = Modifier.padding(2.dp, 60.dp),
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Justify
            )

        }
    }


}


//@Preview
//@Composable
//fun PreviewCarrito() {
//    Column() {
//        MyBodyCarritoContent()
//    }
//}