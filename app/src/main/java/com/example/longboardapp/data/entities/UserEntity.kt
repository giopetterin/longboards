package com.example.longboardapp.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.longboardapp.model.UserModel


@Entity(tableName = "users_table")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "user") val user: String,
    @ColumnInfo(name = "password") val password: String
)

fun UserModel.toDatabase() = UserEntity(user = user, password =  password)