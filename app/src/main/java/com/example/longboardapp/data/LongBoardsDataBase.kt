package com.example.longboardapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.longboardapp.data.dao.LongBoardsDao
import com.example.longboardapp.data.dao.UserDao
import com.example.longboardapp.data.entities.LongBoardsEntity
import com.example.longboardapp.data.entities.UserEntity


@Database(entities = [LongBoardsEntity::class, UserEntity::class], version = 2, exportSchema = false)
abstract class LongBoardsDataBase: RoomDatabase() {

    abstract fun getLongBoardDao():LongBoardsDao

    abstract fun getUserDao():UserDao


}