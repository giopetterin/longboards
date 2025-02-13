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


data class KindOfLongBoards(val tittle: String, val body: String)

val longBoards: List<KindOfLongBoards> = listOf(
    KindOfLongBoards(
        "Dancing",
        "las tablas danding longboard son el tipo de tabla más largo. Estas tablas permiten realizar gran variedad de trucos y movimientos. Este tipo de tabla es ideal para pistas largas con superficies lisas."
    ),
    KindOfLongBoards(
        "Cruising",
        "las tablas de cruising son perfectas para la movilidad urbana ya que permiten tomar curvas más cerradas y al ser menos voluminosas permiten un transporte más ligero."
    ),
    KindOfLongBoards(
        "SurfTStake",
        "tablas diseñadas para vivir la experiencia del surf en el asfalto. Su punto fuerte son sus ejes especiales con los que girar en cualquier dirección y no limitar el movimiento. Su diseño, innovación y tecnología hacen que estas tablas tengan un gran atractivo."
    ),
    KindOfLongBoards(
        "Balance",
        "este tipo de tablas son para realizar ejercicios de equilibrio, fuerza y resistencia. Se apoyan sobre una base inestable y son una opción perfecta para principiantes. Los entrenamientos en casa con este tipo de tabla potencian el equilibrio y permiten un entrenamiento fléxible en cualquier momento."
    ),
    KindOfLongBoards(
        "Dancing",
        "las tablas danding longboard son el tipo de tabla más largo. Estas tablas permiten realizar gran variedad de trucos y movimientos. Este tipo de tabla es ideal para pistas largas con superficies lisas."
    ),
    KindOfLongBoards(
        "Cruising",
        "las tablas de cruising son perfectas para la movilidad urbana ya que permiten tomar curvas más cerradas y al ser menos voluminosas permiten un transporte más ligero."
    ),
    KindOfLongBoards(
        "SurfTStake",
        "tablas diseñadas para vivir la experiencia del surf en el asfalto. Su punto fuerte son sus ejes especiales con los que girar en cualquier dirección y no limitar el movimiento. Su diseño, innovación y tecnología hacen que estas tablas tengan un gran atractivo."
    ),
    KindOfLongBoards(
        "Balance",
        "este tipo de tablas son para realizar ejercicios de equilibrio, fuerza y resistencia. Se apoyan sobre una base inestable y son una opción perfecta para principiantes. Los entrenamientos en casa con este tipo de tabla potencian el equilibrio y permiten un entrenamiento fléxible en cualquier momento."
    )
)

@Composable
fun MyLongBoards(navController: NavController) {
    LazyVerticalGrid(columns = GridCells.Fixed(3)) {
        items(longBoards) { longBoard ->
            CentralMenuOptions(longBoard, navController)
        }
    }
}

@Composable
fun MiniMenuOptions(isExpanded: Boolean, onItemClick: (String) -> Unit, onDismiss: () -> Unit) {
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
fun MyTexts(title: String, body: String) {
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
fun MyText(text: String, color: Color, style: TextStyle, lines: Int = Int.MAX_VALUE) {
    Text(text, color = color, style = style, maxLines = lines)
}