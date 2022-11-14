package com.leyline.xkcd.comic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ComicViewModel : ViewModel() {

    private val _uiState = MutableLiveData<ComicDataModel>()
    val uiState: LiveData<ComicDataModel> = _uiState

    private val _totalNumber = MutableLiveData<Int>()
    val totalNumber: LiveData<Int> = _totalNumber

    val comics = MutableLiveData<MutableList<ComicDataTransferObject>>()
    fun setTotalNumber(latestNumber: Int){
        _totalNumber.value = latestNumber
    }

    fun setOutput(output: MutableList<ComicDataTransferObject>) {
        comics.value = output
    }
}