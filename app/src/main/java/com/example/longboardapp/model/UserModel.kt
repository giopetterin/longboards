package com.example.longboardapp.model

import com.example.longboardapp.data.entities.UserEntity

data class UserModel(val user: String, val password: String)


fun UserModel.toDomain() = UserModel(user, password)
fun UserEntity.toDomain() = UserModel(user, password)