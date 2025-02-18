package com.example.longboardapp.model

import javax.inject.Inject

class LongBoardProvider @Inject constructor() {
    fun getAllLongBoards(): List<LongBoardModel> {
        val longBoards: List<LongBoardModel> = listOf(
            LongBoardModel(
                "Dancing",
                "las tablas danding longboard son el tipo de tabla más largo. Estas tablas permiten realizar gran variedad de trucos y movimientos. Este tipo de tabla es ideal para pistas largas con superficies lisas.",
                100.2

            ),
            LongBoardModel(
                "Cruising",
                "las tablas de cruising son perfectas para la movilidad urbana ya que permiten tomar curvas más cerradas y al ser menos voluminosas permiten un transporte más ligero.",
                60.44
            ),
            LongBoardModel(
                "SurfTStake",
                "tablas diseñadas para vivir la experiencia del surf en el asfalto. Su punto fuerte son sus ejes especiales con los que girar en cualquier dirección y no limitar el movimiento. Su diseño, innovación y tecnología hacen que estas tablas tengan un gran atractivo.",
                50.00
            ),
            LongBoardModel(
                "Balance",
                "este tipo de tablas son para realizar ejercicios de equilibrio, fuerza y resistencia. Se apoyan sobre una base inestable y son una opción perfecta para principiantes. Los entrenamientos en casa con este tipo de tabla potencian el equilibrio y permiten un entrenamiento fléxible en cualquier momento.",
                50.00
            ),
            LongBoardModel(
                "Dancing",
                "las tablas danding longboard son el tipo de tabla más largo. Estas tablas permiten realizar gran variedad de trucos y movimientos. Este tipo de tabla es ideal para pistas largas con superficies lisas.",
                200.33
            ),
            LongBoardModel(
                "Cruising",
                "las tablas de cruising son perfectas para la movilidad urbana ya que permiten tomar curvas más cerradas y al ser menos voluminosas permiten un transporte más ligero.",
                240.12
            ),
            LongBoardModel(
                "SurfTStake",
                "tablas diseñadas para vivir la experiencia del surf en el asfalto. Su punto fuerte son sus ejes especiales con los que girar en cualquier dirección y no limitar el movimiento. Su diseño, innovación y tecnología hacen que estas tablas tengan un gran atractivo.",
                123.99
            ),
            LongBoardModel(
                "Balance",
                "este tipo de tablas son para realizar ejercicios de equilibrio, fuerza y resistencia. Se apoyan sobre una base inestable y son una opción perfecta para principiantes. Los entrenamientos en casa con este tipo de tabla potencian el equilibrio y permiten un entrenamiento fléxible en cualquier momento.",
                10.11
            )
        )



        return longBoards
    }

}
