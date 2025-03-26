package com.example.longboardapp.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.longboardapp.model.LongBoardModel


@Entity(tableName = "longboards_table")
data class LongBoardsEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "tittle") val tittle: String,
    @ColumnInfo(name = "body") val body: String,
    @ColumnInfo(name = "price") val price: Double
)


fun LongBoardModel.toDatabase() = LongBoardsEntity(id = id, tittle = tittle, body =  body, price = price)
