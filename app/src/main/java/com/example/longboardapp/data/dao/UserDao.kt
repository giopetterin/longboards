package com.example.longboardapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.longboardapp.data.entities.UserEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM users_table WHERE user = :user and password = :password " )
    suspend fun getUserByNameAndPassword(user :String, password :String):UserEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user:UserEntity)


}

