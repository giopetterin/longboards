package com.example.longboardapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.longboardapp.Resource
import com.example.longboardapp.data.entities.LongBoardsEntity
import com.example.longboardapp.model.LongBoardModel

@Dao
interface LongBoardsDao {

    @Query("SELECT * FROM longboards_table ORDER BY tittle DESC")
    suspend fun getAllLongBoards(): List<LongBoardsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(longBoards: List<LongBoardsEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun create(longBoard: LongBoardsEntity) : Long

    @Update
    suspend fun update(longBoard: LongBoardsEntity)

    @Delete
    suspend fun delete(longBoard: LongBoardsEntity)


    suspend fun insertItemSafe(longBoard: LongBoardsEntity): Resource<Long> =
        try {
            val resource = create(longBoard)
            Resource.Success(resource)

        } catch (e: Exception) {
            Resource.Error(e)
        }



}

