package com.example.longboardapp.view.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
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
        MyCarritoLongBoards(carritoLongBoards)
    }
}


@Composable
fun MyCarritoLongBoards(carritoLongBoards: List<LongBoardModel>) {

    val longBoards: Set<LongBoardModel> =  carritoLongBoards.toSet()

    LazyHorizontalGrid(rows = GridCells.Fixed(3)) {
        items( longBoards.toList()){ longBoard ->
            MyBodyCarritoContent(longBoard)
        }

    }

}



@Composable
fun MyBodyCarritoContent(longBoard: LongBoardModel) {
    Box(
        modifier = Modifier
            .padding(start = 10.dp, top = 120.dp, end = 10.dp)
            .border(1.dp, MaterialTheme.colorScheme.onPrimary, RoundedCornerShape(5.dp))
            .height(50.dp),
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

//
//@Preview
//@Composable
//fun PreviewCarrito() {
//    Column() {
//        MyCarritoLongBoards()
//    }
//}