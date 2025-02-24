package com.example.longboardapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.longboardapp.data.dao.LongBoardsDao
import com.example.longboardapp.data.entities.LongBoardsEntity


@Database(entities = [LongBoardsEntity::class], version = 1, exportSchema = false)
abstract class LongBoardsDataBase: RoomDatabase() {

    abstract fun getLongBoardDao():LongBoardsDao
}