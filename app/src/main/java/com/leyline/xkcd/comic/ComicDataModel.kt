package com.leyline.xkcd.comic

/**
 * The model for the [ComicViewFragment].
 *
 * @param number the unique id and number of the comic
 * @param title the title of the comic
 */
data class ComicDataModel(
    val number: Int,
    val title: String
)