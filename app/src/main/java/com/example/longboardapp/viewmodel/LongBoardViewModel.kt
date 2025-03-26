package com.example.longboardapp.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.longboardapp.Resource
import com.example.longboardapp.domain.LongBoardsRepository
import com.example.longboardapp.model.LongBoardModel
import com.example.longboardapp.model.LongBoardProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class LongBoardViewModel
@Inject constructor(
    private val longBoardsRepository: LongBoardsRepository,
    //private val longBoardProvider: LongBoardProvider
) : ViewModel() {

    internal var _longBoardsLiveData = MutableLiveData<List<LongBoardModel>>()
    var longBoardsLiveData: LiveData<List<LongBoardModel>> = _longBoardsLiveData

    internal var _isLoading = MutableLiveData<Boolean>()
    var isLoading: LiveData<Boolean> = _isLoading


    private val _error = MutableStateFlow<Exception?>(null)
    val error: StateFlow<Exception?> = _error


    suspend fun onCreate() {

        var result: List<LongBoardModel>
        result = longBoardsRepository.getAllLongBoardsFromDatabase()
        if (result.isEmpty()) {
            result = longBoardsRepository.getAllLongBoardsFromDatabase()
        }
        _longBoardsLiveData.postValue(result)
        _isLoading.value = true

    }


    suspend fun addProduct(products: List<LongBoardModel>, title: String, body: String, price: Double) {
        val newProduct = LongBoardModel(0, title, body, price)
        products.plus(newProduct)
        when( val result = longBoardsRepository.insertLongBoard(newProduct)){
            is Resource.Success -> _error.value = null

            is Resource.Error -> _error.value = result.exception
        }
    }

    suspend fun deleteProduct(products: List<LongBoardModel>, product: LongBoardModel) {
        products.minus(product)


        when( val result = longBoardsRepository.deleteLongBoard(product)){
            is Resource.Success -> _error.value = null

            is Resource.Error -> _error.value = result.exception
        }
    }

    suspend fun editProduct(product: LongBoardModel, title: String, body: String, price: Double) {
        product.tittle = title
        product.body = body
        product.price = price

        when( val result = longBoardsRepository.updateLongBoard(product)){
            is Resource.Success -> _error.value = null

            is Resource.Error -> _error.value = result.exception
        }

    }


}