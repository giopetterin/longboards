package com.example.longboardapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.longboardapp.data.entities.LongBoardsEntity

@Dao
interface LongBoardsDao {

    @Query("SELECT * FROM longboards_table ORDER BY tittle DESC LIMIT 10")
    suspend fun getAllLongBoards():List<LongBoardsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(longBoards:List<LongBoardsEntity>)


}

