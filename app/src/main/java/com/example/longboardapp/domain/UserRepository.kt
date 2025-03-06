package com.example.longboardapp.domain

import com.example.longboardapp.model.LongBoardModel
import com.example.longboardapp.model.UserModel


interface UserRepository {
    suspend fun getUserByNameAndPassword(user :String, password:String) : UserModel

    suspend fun insertUser(user: UserModel)
}