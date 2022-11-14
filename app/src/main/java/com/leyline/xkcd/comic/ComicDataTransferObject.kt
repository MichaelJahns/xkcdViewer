package com.leyline.xkcd.comic

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Comic(
    val num: Int,
    private val month: Int,
    private val link: String?,
    private val year: String,
    private val news: String?,
    private val safe_title: String,
    private val transcript: String,
    private val alt: String,
    private val img: String,
    private val title: String,
    private val day: String,
): Parcelable