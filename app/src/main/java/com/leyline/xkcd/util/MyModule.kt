package com.leyline.xkcd.util

import com.leyline.xkcd.comic.ComicViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val networkingModule = module {
    single { RetrofitObject.getInstance().create(ComicApi::class.java) }
    viewModel { ComicViewModel() }
}
