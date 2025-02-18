package com.example.longboardapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.longboardapp.model.LongBoardModel
import com.example.longboardapp.model.LongBoardProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LongBoardViewModel
    @Inject constructor(private val longBoardProvider: LongBoardProvider)
    : ViewModel() {


    fun getAllLongBoards(): List<LongBoardModel> {
        return longBoardProvider.getAllLongBoards()

    }

}