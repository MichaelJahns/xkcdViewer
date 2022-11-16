package com.leyline.xkcd.comic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leyline.xkcd.util.ComicApi
import com.leyline.xkcd.util.RetrofitObject
import org.koin.java.KoinJavaComponent.inject
import retrofit2.create

class ComicViewModel : ViewModel() {

    private val comicApi: ComicApi by inject(ComicApi::class.java)
    private val _uiState = MutableLiveData<ComicDataTransferObject>()
    val uiState: LiveData<ComicDataTransferObject> = _uiState

    private val latestComicId = MutableLiveData<Int>()
    val currentComicId = MutableLiveData<Int>()
    val showInfoScreen = MutableLiveData<Boolean>()

    fun setInfoScreen(isShowing: Boolean) {
        showInfoScreen.value = isShowing
    }

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
        currentComicId.value = comic.num
        latestComicId.value = comic.num
    }

    fun decrementCurrentComic() {
        val currentComicID = currentComicId.value!!
        if (areThereEarlierComics(currentComicID)) {
            currentComicId.value = currentComicID.minus(1)
        }
    }

    private fun areThereEarlierComics(currentId: Int): Boolean {
        /**Comparison returns positive value if there are older comics than the current. */
        val comparison = currentId.compareTo(1)
        if (comparison > 0) {
            return true
        }
        return false
    }

    fun incrementCurrentComic() {
        val currentComicID: Int = currentComicId.value!!
        if (areThereNewerComics(currentComicID)) {
            currentComicId.value = currentComicID.plus(1)
        }
    }

    private fun areThereNewerComics(currentId: Int): Boolean {
        /**Comparison returns negative value if there are newer comics than the current. */
        val comparison = currentId.compareTo(latestComicId.value!!)
        if (comparison < 0) {
            return true
        }
        return false
    }
}