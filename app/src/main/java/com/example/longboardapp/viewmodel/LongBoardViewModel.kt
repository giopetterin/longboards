package com.example.longboardapp.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.longboardapp.data.repository.LocalLongBoardsRepository
import com.example.longboardapp.domain.LongBoardsRepository
import com.example.longboardapp.model.LongBoardModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LongBoardViewModel
    @Inject constructor(private val longBoardsRepository: LongBoardsRepository)
    : ViewModel() {


        private var longBoardModel=  MutableLiveData<List<LongBoardModel>>()

     fun getAllLongBoards(): List<LongBoardModel> {
         var result: List<LongBoardModel> = ArrayList()
         viewModelScope.launch {
              result = longBoardsRepository.getAllLongBoardsFromDatabase()

             longBoardModel.postValue(result)
         }

        return result
    }

}