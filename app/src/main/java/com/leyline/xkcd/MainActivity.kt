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
        val singleComicViewFragment = SingleComicViewFragment()
        supportFragmentManager
            .beginTransaction()
            .add(R.id.mainFrameLayout, singleComicViewFragment)
            .commit()
    }



//    private suspend fun getAllComics(comicApi: ComicApi, totalNumber: Int) {
//        val beforeTime = System.currentTimeMillis()
//        val output = mutableListOf<ComicDataTransferObject>()
//        var counter: Int = 1
//        while (counter < totalNumber) {
//           if (counter != 404) {
//               val comic = comicApi.getComicByIdAsync(counter)
//               output.add(comic)
//           }
//            counter++
//            val afterTime: Long = System.currentTimeMillis()
//            val delta = afterTime - beforeTime
//            Log.d("Comic #$counter: ", "  Elapsed millis:  $delta")
//        }
//        viewModel.setOutput(output)
//    }
//
//    private fun speedTestRetrofit(comicApi: ComicApi) {
//        viewModel.latestComicId.observe(this) { totalNumber ->
//            lifecycleScope.launch {
//                getAllComics(comicApi, totalNumber)
//            }
//        }
//        lifecycleScope.launch {
//            getLatestComic(comicApi)
//        }
//    }
}