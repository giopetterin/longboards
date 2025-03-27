package com.example.longboardapp.data.entities

import com.example.longboardapp.model.UserModel
import org.junit.Assert.assertEquals
import org.junit.Test

class UserEntityTest {

    @Test
    fun `UserEntity constructor sets values correctly`() {
        val id = 1
        val user = "testUser"
        val password = "testPassword"

        val entity = UserEntity(id, user, password)

        assertEquals(id, entity.id)
        assertEquals(user, entity.user)
        assertEquals(password, entity.password)
    }

    @Test
    fun `UserModel toDatabase converts to UserEntity correctly`() {
        val userModel = UserModel("testUser", "testPassword")
        val userEntity = userModel.toDatabase()

        assertEquals(userModel.user, userEntity.user)
        assertEquals(userModel.password, userEntity.password)
    }

    @Test
    fun `UserEntity default id is 0`(){
        val user = "testUser"
        val password = "testPassword"

        val entity = UserEntity(user = user, password = password)

        assertEquals(0, entity.id)
    }
}