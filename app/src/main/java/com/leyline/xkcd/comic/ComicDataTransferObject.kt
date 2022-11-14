package com.leyline.xkcd.comic

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ComicDataTransferObject(
    val num: Int,
    val month: String,
    val link: String?,
    val year: String,
    val news: String?,
    val safe_title: String,
    val transcript: String,
    val alt: String,
    val img: String,
    val title: String,
    val day: String,
): Parcelable