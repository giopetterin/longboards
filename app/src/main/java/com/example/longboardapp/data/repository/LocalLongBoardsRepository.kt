package com.example.longboardapp.data.repository

import com.example.longboardapp.data.dao.LongBoardsDao
import com.example.longboardapp.data.entities.LongBoardsEntity
import com.example.longboardapp.data.entities.toDatabase
import com.example.longboardapp.domain.LongBoardsRepository
import com.example.longboardapp.model.LongBoardModel

import com.example.longboardapp.model.toDomain
import javax.inject.Inject

class LocalLongBoardsRepository @Inject constructor(
    private val longBoardsDao: LongBoardsDao
) : LongBoardsRepository {

    override suspend fun getAllLongBoardsFromDatabase(): List<LongBoardModel> {
        val response: List<LongBoardsEntity> = longBoardsDao.getAllLongBoards()
        return response.map { it.toDomain() }
    }

    override suspend fun insertLongBoards(longBoards: List<LongBoardModel>) {
        longBoardsDao.insertAll(longBoards.map { it.toDatabase() })
    }

}