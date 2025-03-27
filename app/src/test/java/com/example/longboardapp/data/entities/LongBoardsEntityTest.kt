package com.example.longboardapp.data.entities

import com.example.longboardapp.model.LongBoardModel
import org.junit.Assert.assertEquals
import org.junit.Test

class LongBoardsEntityTest {


    @Test
    fun `LongBoardsEntity constructor sets values correctly`() {
        val id = 1
        val tittle = "Test Title"
        val body = "Test Body"
        val price = 100.0

        val entity = LongBoardsEntity(id, tittle, body, price)

        assertEquals(id, entity.id)
        assertEquals(tittle, entity.tittle)
        assertEquals(body, entity.body)
        assertEquals(price, entity.price, 0.0) // Usamos delta para comparar doubles
    }

    @Test
    fun `LongBoardModel toDatabase converts to LongBoardsEntity correctly`() {
        val model = LongBoardModel(1, "Test Title", "Test Body", 100.0)
        val entity = model.toDatabase()

        assertEquals(model.id, entity.id)
        assertEquals(model.tittle, entity.tittle)
        assertEquals(model.body, entity.body)
        assertEquals(model.price, entity.price, 0.0) // Usamos delta para comparar doubles
    }

    @Test
    fun `LongBoardsEntity default id is 0`() {
        val tittle = "Test Title"
        val body = "Test Body"
        val price = 100.0

        val entity = LongBoardsEntity(tittle = tittle, body = body, price = price)

        assertEquals(0, entity.id)
    }

}