package com.example.longboardapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.longboardapp.Resource
import com.example.longboardapp.domain.LongBoardsRepository
import com.example.longboardapp.model.LongBoardModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class LongBoardViewModelTest {

    @Mock
    private lateinit var longBoardsRepository: LongBoardsRepository


    private lateinit var longBoardViewModel: LongBoardViewModel

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        longBoardViewModel = LongBoardViewModel(longBoardsRepository)

    }

    @Test
    fun `onCreate when database is empty`() = runTest {
        val emptyList = emptyList<LongBoardModel>()
        val populatedList = listOf(LongBoardModel(1, "Title", "Body", 100.0))

        `when`(longBoardsRepository.getAllLongBoardsFromDatabase()).thenReturn(emptyList, populatedList)

        longBoardViewModel.onCreate()

        verify(longBoardsRepository, times(2)).getAllLongBoardsFromDatabase()
        assertEquals(populatedList, longBoardViewModel._longBoardsLiveData.value)
        assertEquals(true, longBoardViewModel._isLoading.value)
    }

    @Test
    fun `onCreate when database is not empty`() = runTest {
        val populatedList = listOf(LongBoardModel(1, "Title", "Body", 100.0))

        `when`(longBoardsRepository.getAllLongBoardsFromDatabase()).thenReturn(populatedList)

        longBoardViewModel.onCreate()

        verify(longBoardsRepository, times(1)).getAllLongBoardsFromDatabase()
        assertEquals(populatedList, longBoardViewModel._longBoardsLiveData.value)
        assertEquals(true, longBoardViewModel._isLoading.value)
    }

    @Test
     fun `addProduct successful insertion`() = runTest {
        val products = mutableListOf<LongBoardModel>()
        val title = "Test Product"
        val body = "Test Body"
        val price = 100.0
        val newProduct = LongBoardModel(0, title, body, price)

        `when`(longBoardsRepository.insertLongBoard(newProduct)).thenReturn(Resource.Success(1L))

        longBoardViewModel.addProduct(products, title, body, price)

        verify(longBoardsRepository).insertLongBoard(newProduct)
        assertEquals(null, longBoardViewModel.error.value)
    }

    @Test
    fun `addProduct insertion error`() = runTest {
        val products = mutableListOf<LongBoardModel>()
        val title = "Test Product"
        val body = "Test Body"
        val price = 100.0
        val newProduct = LongBoardModel(0, title, body, price)
        val exception = Exception("Test Exception")

        `when`(longBoardsRepository.insertLongBoard(newProduct)).thenReturn(Resource.Error(exception))

        longBoardViewModel.addProduct(products, title, body, price)

        verify(longBoardsRepository).insertLongBoard(newProduct)
        assertEquals(exception, longBoardViewModel.error.value)
    }

    @Test
    fun `deleteProduct successful deletion`() = runTest {
        val productToDelete = LongBoardModel(1, "Title", "Body", 100.0)
        val products = mutableListOf(
            LongBoardModel(0, "Other Title", "Other Body", 200.0),
            productToDelete
        )

        `when`(longBoardsRepository.deleteLongBoard(productToDelete)).thenReturn(Resource.Success(Unit))

        longBoardViewModel.deleteProduct(products, productToDelete)

        verify(longBoardsRepository).deleteLongBoard(productToDelete)
        assertEquals(null, longBoardViewModel.error.value)
    }

    @Test
    fun `deleteProduct deletion error`() = runTest {
        val productToDelete = LongBoardModel(1, "Title", "Body", 100.0)
        val products = mutableListOf(
            LongBoardModel(0, "Other Title", "Other Body", 200.0),
            productToDelete
        )
        val exception = Exception("Test Exception")

        `when`(longBoardsRepository.deleteLongBoard(productToDelete)).thenReturn(Resource.Error(exception))

        longBoardViewModel.deleteProduct(products, productToDelete)

        verify(longBoardsRepository).deleteLongBoard(productToDelete)
        assertEquals(exception, longBoardViewModel.error.value)
    }

    @Test
    fun `editProduct successful update`() = runTest {
        val originalProduct = LongBoardModel(1, "Original Title", "Original Body", 100.0)
        val newTitle = "New Title"
        val newBody = "New Body"
        val newPrice = 200.0

        `when`(longBoardsRepository.updateLongBoard(LongBoardModel(1, newTitle, newBody, newPrice))).thenReturn(Resource.Success(Unit))

        longBoardViewModel.editProduct(originalProduct, newTitle, newBody, newPrice)

        verify(longBoardsRepository).updateLongBoard(LongBoardModel(1, newTitle, newBody, newPrice))
        assertEquals(newTitle, originalProduct.tittle)
        assertEquals(newBody, originalProduct.body)
        assertEquals(newPrice, originalProduct.price, 100.0)
        assertEquals(null, longBoardViewModel.error.value)
    }

    @Test
    fun `editProduct update error`() = runTest {
        val originalProduct = LongBoardModel(1, "Original Title", "Original Body", 100.0)
        val newTitle = "New Title"
        val newBody = "New Body"
        val newPrice = 200.0
        val exception = Exception("Test Exception")

        `when`(longBoardsRepository.updateLongBoard(LongBoardModel(1, newTitle, newBody, newPrice))).thenReturn(Resource.Error(exception))

        longBoardViewModel.editProduct(originalProduct, newTitle, newBody, newPrice)

        verify(longBoardsRepository).updateLongBoard(LongBoardModel(1, newTitle, newBody, newPrice))
        assertEquals(newTitle, originalProduct.tittle)
        assertEquals(newBody, originalProduct.body)
        assertEquals(newPrice, originalProduct.price, 100.0)
        assertEquals(exception, longBoardViewModel.error.value)
    }
}