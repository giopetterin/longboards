package com.example.longboardapp.components

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.map
import androidx.navigation.NavController
import com.example.longboardapp.model.LongBoardModel
import com.example.longboardapp.navigation.AppScreens
import com.example.longboardapp.navigation.myRouteLongBoards
import com.example.longboardapp.view.screens.carritoLongBoards
import com.example.longboardapp.viewmodel.LongBoardViewModel


@SuppressLint("CheckResult")
@Composable
fun getAllLongBoardsFromDB() : List<LongBoardModel>{
    val longBoardViewModel = hiltViewModel<LongBoardViewModel>()
    var longBoards: List<LongBoardModel> = mutableListOf()

    longBoardViewModel.onCreate()

    longBoardViewModel.longBoardsLiveData.map { data ->
        longBoards = data
    }

    return longBoards
}

@Composable
fun MyLongBoards(longBoards: List<LongBoardModel>, navController: NavController) {

    LazyVerticalGrid(columns = GridCells.Fixed(3)) {
        items(longBoards) { longBoard ->
            CentralMenuOptions(longBoard, navController)
        }
    }
}

@Composable
fun MiniMenuOptions(longBoards: List<LongBoardModel>, isExpanded: Boolean, onItemClick: (String) -> Unit, onDismiss: () -> Unit) {
    DropdownMenu(isExpanded, onDismiss) {

        longBoards.forEach(){ longBoard ->
            DropdownMenuItem(text = { Text(longBoard.tittle) },
                onClick = {
                    onItemClick(longBoard.tittle)
                    onDismiss()
                })
        }
    }
}


@Composable
fun CentralMenuOptions(longBoards: LongBoardModel, navController: NavController) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                myRouteLongBoards(longBoards.tittle, navController)
            }, contentAlignment = Alignment.TopCenter
    ) {
        MyMenuImageLongBoards(longBoards.tittle)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier
                .padding(vertical = 80.dp),
            text = longBoards.tittle,
            textAlign = TextAlign.End
        )


    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyScaffoldTopBarAndBody(tittle: String, longBoard: LongBoardModel, navController: NavController){

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
                            Text(tittle,
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
                    Text(tittle)
                }
            )
        }
    ) {
        MyBodyLongBoardContent(longBoard)
    }
}

@Composable
fun MyBodyLongBoardContent(longBoard: LongBoardModel) {
    Box(
        modifier = Modifier
            .padding(start = 10.dp, top = 120.dp, end= 10.dp)
            .border(1.dp, MaterialTheme.colorScheme.onPrimary, RoundedCornerShape(5.dp))
            .height(400.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MyImageLongBoards(longBoard.tittle)
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

@Composable
fun MyDualTextsExpanded(title: String, body: String) {
    var expanded by remember { mutableStateOf(false) }
    Column(modifier = Modifier
        .padding(8.dp)
        .clickable {
            expanded = !expanded
        }) {
        MyText(
            title,
            MaterialTheme.colorScheme.primary,
            MaterialTheme.typography.titleMedium,
            if (expanded) Int.MAX_VALUE else 1
        )
        Spacer(modifier = Modifier.height(10.dp))
        MyText(
            body,
            MaterialTheme.colorScheme.primary,
            MaterialTheme.typography.titleSmall,
            if (expanded) Int.MAX_VALUE else 1
        )
    }
}



@Composable
fun MyText(text: String, color: Color, style: TextStyle, lines: Int = Int.MAX_VALUE, align: TextAlign? = null) {
    Text(text, color = color, style = style, maxLines = lines, textAlign = align)
}