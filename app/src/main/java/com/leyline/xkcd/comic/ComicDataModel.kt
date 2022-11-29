package com.leyline.xkcd.comic

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ComicDataModel(
    val num: Int,
    val month: String,
    val link: String?,
    val year: String,
    val news: String?,
    @SerializedName("safe_title")
    val safeTitle: String,
    val transcript: String,
    val alt: String,
    val img: String,
    val title: String,
    val day: String,
): Parcelable