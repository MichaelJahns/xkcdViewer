package com.leyline.xkcd

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.leyline.xkcd.comic.ComicViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: ComicViewModel
    private lateinit var singleComicViewFragment: SingleComicViewFragment
    private lateinit var singleComicInfoFragment: SingleComicInfoFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this)[ComicViewModel::class.java]
        singleComicViewFragment = SingleComicViewFragment()
        singleComicInfoFragment = SingleComicInfoFragment()
        initObservers()
    }

    private fun initObservers() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.mainFrameLayout, singleComicViewFragment)
            .commit()

        viewModel.showInfoScreen.observe(this) { isShowing ->
            if (isShowing) {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.mainFrameLayout, singleComicInfoFragment)
                    .addToBackStack("SingleComicViewFragment")
                    .commit()
            } else {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.mainFrameLayout, singleComicViewFragment)
                    .commit()
            }
        }
        viewModel.setInfoScreen(isShowing = false)
    }
}