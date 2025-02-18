package com.example.longboardapp.domain

import com.example.longboardapp.model.LongBoardModel


interface LongBoardsRepository {
    suspend fun getAllLongBoardsFromDatabase() : List<LongBoardModel>
}