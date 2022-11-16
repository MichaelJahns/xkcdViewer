package com.leyline.xkcd

import com.leyline.xkcd.comic.ComicViewModel
import com.leyline.xkcd.util.ComicApi
import com.leyline.xkcd.util.RetrofitObject
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val networkingModule = module {
    single { RetrofitObject.getInstance().create(ComicApi::class.java) }
    viewModel { ComicViewModel() }
}
