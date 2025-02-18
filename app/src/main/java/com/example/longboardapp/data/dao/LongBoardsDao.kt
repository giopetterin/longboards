package com.example.longboardapp.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.longboardapp.data.entities.LongBoardsEntity

@Dao
interface LongBoardsDao {

    @Query("SELECT * FROM longboards_table ORDER BY tittle DESC")
    suspend fun getAllLongBoards():List<LongBoardsEntity>
}

