package com.example.longboardapp.viewmodel


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.longboardapp.domain.LongBoardsRepository
import com.example.longboardapp.model.LongBoardModel
import com.example.longboardapp.model.LongBoardProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LongBoardViewModel
    @Inject constructor(private val longBoardsRepository: LongBoardsRepository,
    private val longBoardProvider: LongBoardProvider)
    : ViewModel() {


        private var longBoardModel=  MutableLiveData<List<LongBoardModel>>()

     fun getAllLongBoards(): List<LongBoardModel> {
         var result: List<LongBoardModel> = ArrayList()
         viewModelScope.launch {

             result = longBoardsRepository.getAllLongBoardsFromDatabase()
             if ((0).equals(result.size)) {
                 longBoardsRepository.insertLongBoards(longBoardProvider.getAllLongBoards())

                 result = longBoardsRepository.getAllLongBoardsFromDatabase()
             }

             longBoardModel.postValue(result)
         }

        return result
    }



}