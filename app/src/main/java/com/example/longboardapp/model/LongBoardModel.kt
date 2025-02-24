package com.example.longboardapp.model

import com.example.longboardapp.data.entities.LongBoardsEntity

data class LongBoardModel(val tittle: String, val body: String, val price: Double)


fun LongBoardModel.toDomain() = LongBoardModel(tittle, body, price)
fun LongBoardsEntity.toDomain() = LongBoardModel(tittle, body, price)