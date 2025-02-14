package com.example.longboardapp.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.navigation.NavController
import com.example.longboardapp.navigation.myRouteLongBoards
import com.example.longboardapp.screens.KindOfLongBoards


@Composable
fun MyLongBoards(longBoards: List<KindOfLongBoards>, navController: NavController) {
    LazyVerticalGrid(columns = GridCells.Fixed(3)) {
        items(longBoards) { longBoard ->
            CentralMenuOptions(longBoard, navController)
        }
    }
}

@Composable
fun MiniMenuOptions(longBoards: List<KindOfLongBoards>, isExpanded: Boolean, onItemClick: (String) -> Unit, onDismiss: () -> Unit) {
    DropdownMenu(isExpanded, onDismiss) {
        longBoards.forEach { longBoard ->
            DropdownMenuItem(text = { Text(longBoard.tittle) },
                onClick = {
                    onItemClick(longBoard.tittle)
                    onDismiss()
                })
        }
    }
}


@Composable
fun CentralMenuOptions(longBoards: KindOfLongBoards, navController: NavController) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                myRouteLongBoards(longBoards.tittle, navController)
            }, contentAlignment = Alignment.TopCenter
    ) {
        MyImageLongBoards(longBoards.tittle)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier
                .padding(vertical = 80.dp),
            text = longBoards.tittle,
            textAlign = TextAlign.End
        )


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