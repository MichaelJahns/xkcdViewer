package com.leyline.xkcd.comic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leyline.xkcd.util.ComicApi
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

val comic404 = ComicDataModel(
    404,
    "Unknown",
    "",
    "Unknown",
    "",
    "404 Not Found",
    "",
    "404 Joke Not Found",
    "",
    "404 Not Found",
    "Unknown"
)

class ComicViewModel: ViewModel() {

    private val comicApi: ComicApi by inject(ComicApi::class.java)
    private val _uiState = MutableLiveData<ComicDataModel>()
    val uiState: LiveData<ComicDataModel> = _uiState

    private val latestComicId = MutableLiveData<Int>()
    val currentComicId = MutableLiveData<Int>()

    fun getComicById(id: Int) {
        viewModelScope.launch {
            // Randal Monroe, funny man, has the url for comic #404 open a 404 cant find
            if (id == 404) {
                _uiState.value = comic404
            } else {
                val comic = comicApi.getComicByIdAsync(id)
                _uiState.value = comic
            }
        }
    }

    fun requestFirstComic() {
        viewModelScope.launch {
            val comic = comicApi.getComicByIdAsync(1)
            currentComicId.value = comic.num
        }
    }

   fun requestComicById(id: Int) {
        /**If latestComicId is null call requestLatestComic() */
       viewModelScope.launch {
        if (latestComicId.value != null) {
            /** When a request goes out of bounds set current id to the mock 404 error comic */
            if (id > latestComicId.value!! || id < 1) {
                currentComicId.value = 404
            } else {
                currentComicId.value = id
            }
        } else {
            requestLatestComic()
            requestComicById(id)
        }
       }
    }

    /**This is a required call. if latestComicId is null the above fails. */
    fun requestLatestComic(){
        viewModelScope.launch {
            val comic = comicApi.getLatestComicAsync()
            currentComicId.value = comic.num
            latestComicId.value = comic.num
        }
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