package com.example.longboardapp.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.longboardapp.domain.LongBoardsRepository
import com.example.longboardapp.model.LongBoardModel
import com.example.longboardapp.model.LongBoardProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LongBoardViewModel
@Inject constructor(
    private val longBoardsRepository: LongBoardsRepository,
    private val longBoardProvider: LongBoardProvider
) : ViewModel() {



    suspend fun getAllLongBoards(): List<LongBoardModel> {
        var result: List<LongBoardModel> = longBoardProvider.getAllLongBoards()
        viewModelScope.launch {
            withContext(Dispatchers.Main){
                result = longBoardsRepository.getAllLongBoardsFromDatabase()
                if (!result.any()) {
                    longBoardsRepository.insertLongBoards(longBoardProvider.getAllLongBoards())
                    result = longBoardsRepository.getAllLongBoardsFromDatabase()
                }
            }
        }

                return result

    }


}