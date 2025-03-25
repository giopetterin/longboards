package com.example.longboardapp.model

import com.example.longboardapp.data.entities.LongBoardsEntity

data class LongBoardModel(
    var id: Int, //atributo opcional
    var tittle: String,
    var body: String,
    var price: Double)
{constructor() : this(0, "", "", 0.0)
}


fun LongBoardModel.toDomain() = LongBoardModel(id,tittle, body, price)
fun LongBoardsEntity.toDomain() = LongBoardModel(id, tittle, body, price)