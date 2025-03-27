package com.example.longboardapp.data.repository

import com.example.longboardapp.Resource
import com.example.longboardapp.data.dao.LongBoardsDao
import com.example.longboardapp.data.entities.LongBoardsEntity
import com.example.longboardapp.data.entities.toDatabase
import com.example.longboardapp.model.LongBoardModel
import com.example.longboardapp.model.toDomain
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class LocalLongBoardsRepositoryTest {
    @Mock
    private lateinit var longBoardsDao: LongBoardsDao

    private lateinit var repository: LocalLongBoardsRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        repository = LocalLongBoardsRepository(longBoardsDao)
    }

    @Test
    fun `getAllLongBoardsFromDatabase returns List of LongBoardModel`() = runTest {
        val entities = listOf(
            LongBoardsEntity(1, "Title1", "Body1", 100.0),
            LongBoardsEntity(2, "Title2", "Body2", 200.0)
        )
        val models = entities.map { it.toDomain() }

        `when`(longBoardsDao.getAllLongBoards()).thenReturn(entities)

        val result = repository.getAllLongBoardsFromDatabase()

        assertEquals(models, result)
        verify(longBoardsDao).getAllLongBoards()
    }

    @Test
    fun `insertLongBoards calls insertAll with correct entities`() = runTest {
        val models = listOf(
            LongBoardModel(1, "Title1", "Body1", 100.0),
            LongBoardModel(2, "Title2", "Body2", 200.0)
        )
        val entities = models.map { it.toDatabase() }

        repository.insertLongBoards(models)

        verify(longBoardsDao).insertAll(entities)
    }

    @Test
    fun `insertLongBoard calls insertItemSafe with correct entity`() = runTest {
        val model = LongBoardModel(1, "Title1", "Body1", 100.0)
        val entity = model.toDatabase()
        val expectedResult = Resource.Success(1L)

        `when`(longBoardsDao.insertItemSafe(entity)).thenReturn(expectedResult)

        val result = repository.insertLongBoard(model)

        assertEquals(expectedResult, result)
        verify(longBoardsDao).insertItemSafe(entity)
    }

    @Test
    fun `updateLongBoard calls updateItemSafe with correct entity`() = runTest {
        val model = LongBoardModel(1, "Title1", "Body1", 100.0)
        val entity = model.toDatabase()
        val expectedResult = Resource.Success(Unit)

        `when`(longBoardsDao.updateItemSafe(entity)).thenReturn(expectedResult)

        val result = repository.updateLongBoard(model)

        assertEquals(expectedResult, result)
        verify(longBoardsDao).updateItemSafe(entity)
    }

    @Test
    fun `deleteLongBoard calls deleteItemSafe with correct entity`() = runTest {
        val model = LongBoardModel(1, "Title1", "Body1", 100.0)
        val entity = model.toDatabase()
        val expectedResult = Resource.Success(Unit)

        `when`(longBoardsDao.deleteItemSafe(entity)).thenReturn(expectedResult)

        val result = repository.deleteLongBoard(model)

        assertEquals(expectedResult, result)
        verify(longBoardsDao).deleteItemSafe(entity)
    }
}