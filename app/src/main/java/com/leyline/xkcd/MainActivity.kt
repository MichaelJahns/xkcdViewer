package com.leyline.xkcd

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.leyline.xkcd.comic.ComicDataTransferObject
import com.leyline.xkcd.comic.ComicViewModel
import com.leyline.xkcd.util.ComicApi
import com.leyline.xkcd.util.RetrofitObject
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: ComicViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(ComicViewModel::class.java)

        val comicApi = RetrofitObject.getInstance().create(ComicApi::class.java)
//        cahgne
        speedTestRetrofit(comicApi)
    }

    private suspend fun getLatestComic(comicApi: ComicApi) {
        val result = comicApi.getLatestComicAsync()
        Log.d("Mikey: ", result.toString())
        viewModel.setTotalNumber(result.num)
    }

    private suspend fun getAllComics(comicApi: ComicApi, totalNumber: Int) {
        val beforeTime = System.currentTimeMillis()
        val output = mutableListOf<ComicDataTransferObject>()
        var counter: Long = 1
        while (counter < totalNumber) {
//      This issue didnt happen before. I legit wonder if the api got updated
//      All of a sudden comic 404 is returning an actual 404 error
//      I easily got all 3000~ before, but they were wrapped in deferred before so maybe the 404 error didnt matter then?

            if (counter != 404L) {
                val comic = comicApi.getComicByIdAsync(counter)
                output.add(comic)
            }
            counter++
            val afterTime: Long = System.currentTimeMillis()
            val delta = afterTime - beforeTime
            Log.d("Comic #$counter: ", "  Elapsed millis:  $delta")
        }
        viewModel.setOutput(output)
    }

    private fun speedTestRetrofit(comicApi: ComicApi) {
        viewModel.comics.observe(this) { comics ->
            Log.d("Mikey", comics.size.toString())
        }
        viewModel.totalNumber.observe(this) { totalNumber ->
            lifecycleScope.launch {
                getAllComics(comicApi, totalNumber)
            }
        }
        lifecycleScope.launch {
            getLatestComic(comicApi)
        }
    }
}