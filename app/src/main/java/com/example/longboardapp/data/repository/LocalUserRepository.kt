package com.example.longboardapp.data.repository

import com.example.longboardapp.data.dao.UserDao
import com.example.longboardapp.data.entities.UserEntity
import com.example.longboardapp.data.entities.toDatabase
import com.example.longboardapp.domain.UserRepository
import com.example.longboardapp.model.UserModel
import com.example.longboardapp.model.toDomain
import javax.inject.Inject

class LocalUserRepository @Inject constructor(
    private val userDao: UserDao
) : UserRepository {

    override suspend fun getUserByNameAndPassword(user :String, password:String): UserModel {
        val response: UserEntity = userDao.getUserByNameAndPassword(user, password)
        return response.toDomain()
    }

    override suspend fun insertUser(user: UserModel) {
        userDao.insert(user.toDatabase())
    }

}


