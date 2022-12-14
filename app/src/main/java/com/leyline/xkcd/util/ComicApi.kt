package com.leyline.xkcd.util

import com.leyline.xkcd.comic.ComicDataModel
import retrofit2.http.GET
import retrofit2.http.Path

interface ComicApi {
    @GET("info.0.json")
    suspend fun getLatestComicAsync() : ComicDataModel

    @GET("{id}/info.0.json")
    suspend fun getComicByIdAsync(@Path("id") id: Int) : ComicDataModel
}