package com.example.longboardapp.data.repository

import com.example.longboardapp.data.dao.UserDao
import com.example.longboardapp.data.entities.UserEntity
import com.example.longboardapp.data.entities.toDatabase
import com.example.longboardapp.model.UserModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class LocalUserRepositoryTest {
    @Mock
    private lateinit var userDao: UserDao

    private lateinit var userRepository: LocalUserRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        userRepository = LocalUserRepository(userDao)
    }

    @Test
    fun `getUserByNameAndPassword returns UserModel`() = runTest {
        val username = "testUser"
        val password = "testPassword"
        val userEntity = UserEntity(1, username, password)
        val userModel = UserModel(username, password)

        `when`(userDao.getUserByNameAndPassword(username, password)).thenReturn(userEntity)

        val result = userRepository.getUserByNameAndPassword(username, password)

        assertEquals(userModel, result)
        verify(userDao).getUserByNameAndPassword(username, password)
    }

    @Test
    fun `insertUser calls userDao insert`() = runTest {
        val userModel = UserModel("testUser", "testPassword")
        val userEntity = userModel.toDatabase()

        userRepository.insertUser(userModel)

        verify(userDao).insert(userEntity)
    }
}