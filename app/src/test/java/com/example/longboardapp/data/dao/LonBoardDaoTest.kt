package com.example.longboardapp.data.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.longboardapp.Resource
import com.example.longboardapp.data.LongBoardsDataBase
import com.example.longboardapp.data.entities.LongBoardsEntity
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class LongBoardsDaoTest {

    private lateinit var database: LongBoardsDataBase // Reemplaza AppDatabase con tu Database
    private lateinit var longBoardsDao: LongBoardsDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            LongBoardsDataBase::class.java // Reemplaza AppDatabase con tu Database
        )
            .allowMainThreadQueries()
            .build()
        longBoardsDao = database.getLongBoardDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun `getAllLongBoards returns List of LongBoardsEntity ordered by title DESC`() = runTest {
        val longBoards1 = LongBoardsEntity(id= 1, tittle = "Z", body = "Body1", price = 100.0)
        val longBoards2 = LongBoardsEntity(id= 2, tittle = "A", body = "Body2", price = 200.0)
        longBoardsDao.insertAll(listOf(longBoards1, longBoards2))

        val result = longBoardsDao.getAllLongBoards()

        assertEquals(listOf(longBoards1, longBoards2), result)
    }

    @Test
    fun `insertAll inserts list of LongBoardsEntity`() = runTest {
        val longBoards = listOf(
            LongBoardsEntity(id= 1, tittle = "Title1", body = "Body1", price = 100.0),
            LongBoardsEntity(id=2 , tittle = "Title2", body = "Body2", price = 200.0)
        )
        longBoardsDao.insertAll(longBoards)

        val result = longBoardsDao.getAllLongBoards().sortedBy { it.id }

        assertEquals(longBoards, result)
    }

    @Test
    fun `create inserts LongBoardsEntity and returns id`() = runTest {
        val longBoard = LongBoardsEntity(tittle = "Title", body = "Body", price = 100.0)
        val id = longBoardsDao.create(longBoard)

        assertEquals(1L, id) // Assuming the first inserted item gets id 1
    }

    @Test
    fun `update updates LongBoardsEntity`() = runTest {
        val longBoard = LongBoardsEntity(tittle = "Title", body = "Body", price = 100.0)
        longBoardsDao.create(longBoard)

        val insertedLongBoard = longBoardsDao.getAllLongBoards().first()

        val updatedLongBoard = insertedLongBoard.copy(tittle = "Updated Title")
        longBoardsDao.update(updatedLongBoard)

        val result = longBoardsDao.getAllLongBoards().first()
        assertEquals("Updated Title", result.tittle)
    }

    @Test
    fun `delete deletes LongBoardsEntity`() = runTest {
        val longBoard = LongBoardsEntity(id = 1, tittle = "Title", body = "Body", price = 100.0)
        longBoardsDao.create(longBoard)
        longBoardsDao.delete(longBoard)

        val result = longBoardsDao.getAllLongBoards()
        assertEquals(emptyList<LongBoardsEntity>(), result)
    }

    @Test
    fun `insertItemSafe returns Success on successful insert`() = runTest {
        val longBoard = LongBoardsEntity(tittle = "Title", body = "Body", price = 100.0)
        val result = longBoardsDao.insertItemSafe(longBoard)
        assertEquals(Resource.Success(1L), result)
    }

    @Test
    fun `updateItemSafe returns Success on successful update`() = runTest {
        val longBoard = LongBoardsEntity(tittle = "Title", body = "Body", price = 100.0)
        longBoardsDao.create(longBoard)
        val updatedLongBoard = longBoard.copy(tittle = "Updated Title")
        val result = longBoardsDao.updateItemSafe(updatedLongBoard)
        assertEquals(Resource.Success(Unit), result)
    }

    @Test
    fun `deleteItemSafe returns Success on successful delete`() = runTest {
        val longBoard = LongBoardsEntity(tittle = "Title", body = "Body", price = 100.0)
        longBoardsDao.create(longBoard)
        val result = longBoardsDao.deleteItemSafe(longBoard)
        assertEquals(Resource.Success(Unit), result)
    }
}