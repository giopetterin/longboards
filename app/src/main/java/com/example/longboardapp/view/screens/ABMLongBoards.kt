package com.example.longboardapp.view.screens


import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.longboardapp.model.LongBoardModel
import com.example.longboardapp.navigation.AppScreens
import com.example.longboardapp.viewmodel.LongBoardViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ABMLongBoards(navController: NavController) {

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
                            navController.navigate(route = AppScreens.MenuPrincipal.route)
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
                                .padding(start = 2.dp, top = 8.dp, end = 2.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                "ABM Productos",
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }
                },
                title = {
                    Text("ABM Productos")
                }
            )
        }
    ) {
        ListaProductos(navController)
    }
}


@SuppressLint("MutableCollectionMutableState", "CoroutineCreationDuringComposition")
@Composable
fun ListaProductos(navController: NavController) {

    val longBoardViewModel = hiltViewModel<LongBoardViewModel>()
    val coroutineScope = rememberCoroutineScope()
    val isLoaded: Boolean by longBoardViewModel.isLoading.observeAsState(initial = false)
    var mostrarFormulario by remember { mutableStateOf(false) }
    val error by  longBoardViewModel.error.collectAsState()

    if (!isLoaded) {
        coroutineScope.launch {
            longBoardViewModel.onCreate()

        }
    }


    val products: List<LongBoardModel> by longBoardViewModel.longBoardsLiveData.observeAsState(
        initial = mutableListOf()
    )

    var productoEditando by remember { mutableStateOf(LongBoardModel()) }
    var nuevoNombre by remember { mutableStateOf("") }
    var nuevoDescripcion by remember { mutableStateOf("") }
    var nuevoPrecio by remember { mutableDoubleStateOf(0.0) }

    if (error != null) {
        Column(Modifier
            .fillMaxWidth()
            .padding(top = 150.dp)) {
            Text(text = "Error: ${error?.message}", color = Color.Red)
            Thread.sleep(9000)
        }
    }
    else {

        Column(Modifier
            .fillMaxWidth()
            .padding(5.dp, 150.dp, 5.dp)) {
            LazyColumn {
                items(products) { producto ->
                    ProductoItem(producto = producto, onEliminar = {
                        coroutineScope.launch {
                            longBoardViewModel.deleteProduct(products, producto)
                        }
                        navController.navigate(route = AppScreens.ABMLongBoards.route)
                    }, onModificar = {
                        productoEditando = producto
                        nuevoNombre = producto.tittle
                        nuevoDescripcion = producto.body
                        nuevoPrecio = producto.price
                    })
                }
            }

            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                Button(onClick = {
                    mostrarFormulario = !mostrarFormulario

                }) {
                    Text(if (mostrarFormulario) "Ocultar Formulario" else "Agregar Producto")
                }
            }
        }

        if (productoEditando.id > 0) {
            Dialog(onDismissRequest = { productoEditando = LongBoardModel() }) {
                Column(Modifier.background(Color.White)) {
                    TextField(
                        value = nuevoNombre,
                        onValueChange = { nuevoNombre = it },
                        label = { Text("Nombre") })
                    TextField(
                        value = nuevoDescripcion,
                        onValueChange = { nuevoDescripcion = it },
                        label = { Text("Descripcion") })
                    TextField(
                        value = nuevoPrecio.toString(),
                        onValueChange = { nuevoPrecio = it.toDouble() },
                        label = { Text("precio") })
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                        Button(onClick = {
                            val res = runBlocking {
                                async {
                                    longBoardViewModel.editProduct(
                                        productoEditando,
                                        nuevoNombre,
                                        nuevoDescripcion,
                                        nuevoPrecio
                                    )
                                }
                            }
                            coroutineScope.launch {
                                res.join()
                            }

                            productoEditando = LongBoardModel()
                            navController.navigate(route = AppScreens.ABMLongBoards.route)
                        }) {
                            Text("Guardar")
                        }
                        Button(onClick = {
                            productoEditando = LongBoardModel()
                            navController.navigate(route = AppScreens.ABMLongBoards.route)
                        }) {
                            Text("Cancelar")
                        }
                    }
                }
            }
        }

        if (mostrarFormulario) {

            AltaProductoForm(products, navController, coroutineScope, longBoardViewModel)
        }
    }
}

@Composable
fun ProductoItem(producto: LongBoardModel, onEliminar: () -> Unit, onModificar: () -> Unit) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text("${producto.tittle} - $${producto.price}")
        Row {
            IconButton(onClick = onModificar) {
                Icon(Icons.Default.Edit, contentDescription = "Modificar")
            }
            IconButton(onClick = onEliminar) {
                Icon(Icons.Default.Delete, contentDescription = "Eliminar")
            }
        }
    }
}

@Composable
fun AltaProductoForm(
    products: List<LongBoardModel>,
    navController: NavController,
    coroutineScope: CoroutineScope,
    longBoardViewModel: LongBoardViewModel
) {
    var tittle by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var price by remember { mutableDoubleStateOf(0.0) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp, 130.dp, 5.dp)
    ) {
        TextField(
            value = tittle,
            onValueChange = { tittle = it },
            label = { Text("Tittle") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = price.toString(),
            onValueChange = { price = it.toDouble() },
            label = { Text("Price") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = {
                coroutineScope.launch {
                    longBoardViewModel.addProduct(products, tittle, description, price)
                }
                navController.navigate(route = AppScreens.ABMLongBoards.route)
            }) {
                Text("Guardar")
            }
        }
    }
}

