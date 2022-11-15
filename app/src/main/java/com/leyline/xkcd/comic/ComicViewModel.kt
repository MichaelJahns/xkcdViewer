package com.leyline.xkcd.comic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leyline.xkcd.util.ComicApi
import com.leyline.xkcd.util.RetrofitObject

class ComicViewModel : ViewModel() {
    companion object {
        // De-couple with dependency injection
        val comicApi: ComicApi = RetrofitObject.getInstance().create(ComicApi::class.java)
    }

    private val _uiState = MutableLiveData<ComicDataTransferObject>()
    val uiState: LiveData<ComicDataTransferObject> = _uiState

    val latestComicId = MutableLiveData<Int>()
    val currentComicId = MutableLiveData<Int>()

    suspend fun getComicById(id: Int) {
        val comic = comicApi.getComicByIdAsync(id)
        _uiState.value = comic
    }

    suspend fun getFirstComic() {
        val comic = comicApi.getComicByIdAsync(1)
        currentComicId.value = comic.num
    }
    suspend fun getNewestComic() {
        val comic = comicApi.getLatestComicAsync()
        currentComicId.value = comic.num
    }

    suspend fun updateLatestComicId() {
        val comic = comicApi.getLatestComicAsync()
        latestComicId.value = comic.num
    }

    fun decrementCurrentComic() {
        val currentComicID = currentComicId.value!!
        if (areThereEarlierComics(currentComicID)) {
            currentComicId.value = currentComicID.minus(1)
        }
    }

    fun incrementCurrentComic() {
        val currentComicID: Int = currentComicId.value!!
        if (areThereNewerComics(currentComicID)) {
            currentComicId.value = currentComicID.plus(1)
        }
    }

    private fun areThereEarlierComics(currentId: Int): Boolean {
        val comparison = currentId.compareTo(1)
        if (comparison > 0) {
            return true
        }
        return false
    }

    private fun areThereNewerComics(currentId: Int): Boolean {
        val comparison = currentId.compareTo(latestComicId.value!!)
        if (comparison < 0) {
            return true
        }
        return false
    }
}