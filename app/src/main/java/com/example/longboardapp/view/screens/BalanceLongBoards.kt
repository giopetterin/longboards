package com.example.longboardapp.view.screens

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.longboardapp.components.MyScaffoldTopBarAndBody
import com.example.longboardapp.components.getAllLongBoardsFromDB
import com.example.longboardapp.model.LongBoardModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition",
    "CheckResult"
)
@Composable
fun BalanceLongBoard(navController: NavController) {

    val longBoards: List<LongBoardModel> = getAllLongBoardsFromDB()

    var varLocalLB = LongBoardModel("","", 0.0)

    longBoards.map { l ->  if (l.tittle == "Balance")  varLocalLB = l }


    MyScaffoldTopBarAndBody("Welcome Balance LongBoard",
        varLocalLB,
        navController)
}

