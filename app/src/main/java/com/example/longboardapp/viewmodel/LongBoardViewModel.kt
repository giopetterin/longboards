package com.example.longboardapp.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.longboardapp.domain.LongBoardsRepository
import com.example.longboardapp.model.LongBoardModel
import com.example.longboardapp.model.LongBoardProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LongBoardViewModel
@Inject constructor(
    private val longBoardsRepository: LongBoardsRepository,
    private val longBoardProvider: LongBoardProvider
) : ViewModel() {

    private val _longBoardsLiveData = MutableLiveData<List<LongBoardModel>>()
    var longBoardsLiveData: LiveData<List<LongBoardModel>> = _longBoardsLiveData

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    suspend fun onCreate() {

        var result: List<LongBoardModel>
        result = longBoardsRepository.getAllLongBoardsFromDatabase()
        if (result.isEmpty()) {
            longBoardsRepository.insertLongBoards(longBoardProvider.getAllLongBoards())
            result = longBoardsRepository.getAllLongBoardsFromDatabase()
        }
        _longBoardsLiveData.postValue(result)
        _isLoading.value = true

    }


}