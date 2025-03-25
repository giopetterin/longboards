package com.example.longboardapp.domain

import com.example.longboardapp.Resource
import com.example.longboardapp.data.entities.LongBoardsEntity
import com.example.longboardapp.model.LongBoardModel


interface LongBoardsRepository {
    suspend fun getAllLongBoardsFromDatabase() : List<LongBoardModel>

    suspend fun insertLongBoards(longBoards: List<LongBoardModel>)

    suspend fun insertLongBoard(longBoard: LongBoardModel) : Resource<Long>

    suspend fun updateLongBoard(longBoard: LongBoardModel)

    suspend fun deleteLongBoard(longBoard: LongBoardModel)
}