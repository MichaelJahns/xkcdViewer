package com.leyline.xkcd

import com.leyline.xkcd.comic.ComicDataModel
import com.leyline.xkcd.comic.ComicViewModel
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import kotlin.test.assertEquals

@RunWith(RobolectricTestRunner::class)
@Config(
    manifest = Config.NONE,
    application = TestMyApplication::class
)
class ComicViewModelTest {
    private var comicViewModel = ComicViewModel()

    @Before
    fun setup() = runBlocking {
        comicViewModel.setInfoScreen(false)
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun incrementCurrentComic_doesNotExceedBounds() = runBlocking {
        comicViewModel.requestLatestComic()
        val firstCapture: Int = comicViewModel.currentComicId.value!!
        comicViewModel.incrementCurrentComic()
        val secondCapture: Int = comicViewModel.currentComicId.value!!

        val firstComparison = firstCapture.compareTo(2600) > 0
        assertEquals(
            message = "Expected id of latest comic to be above 2600",
            expected = true,
            actual = firstComparison
        )
        assertEquals(
            message = "Expected id to be unaffected by increment",
            expected = firstCapture,
            actual = secondCapture
        )
    }

    @Test
    fun decrementCurrentComic_doesNotGoBelowBounds() = runBlocking {
        comicViewModel.requestFirstComic()
        val firstCapture: Int? = comicViewModel.currentComicId.value
        comicViewModel.decrementCurrentComic()
        val secondCapture: Int? = comicViewModel.currentComicId.value
        assertEquals(
            message = "Expected id of first comic to be one",
            expected = 1,
            actual = firstCapture
        )
        assertEquals(
            message = "Expected id to be unaffected by decrement",
            expected = firstCapture,
            actual = secondCapture
        )
        assertEquals(
            message = "Expected id to remain on the first comic",
            expected = 1,
            actual = secondCapture
        )
    }

    @Test
    fun requestComic1000_confirmUiState() = runBlocking {
        comicViewModel.getComicById(1000)
        val model: ComicDataModel = comicViewModel.uiState.value!!
        assertEquals(message = "Expected num to be 1000", expected = 1000, actual = model.num)
        assertEquals(message = "Expected month to be 1", expected = "1", actual = model.month)
        assertEquals(
            message = "Expected link to be https://xkcd.com/1000/large/",
            expected = "https://xkcd.com/1000/large/",
            actual = model.link
        )
        assertEquals(message = "Expected year to be 2012", expected = "2012", actual = model.year)
        assertEquals(message = "Expected news to be empty ", expected = "", actual = model.news)
        assertEquals(
            message = "Expected safe_title to be 1000 Comics",
            expected = "1000 Comics",
            actual = model.safeTitle
        )
        assertEquals(
            message = "Expected transcript to be ",
            expected = "[[1000 characters, numerous of which have appeared previously in other comics, are arranged to create the number \"1000\". Two more people stand in the foreground commenting on the formation]]\n" +
                    "\n" +
                    "Person 1: WOOOO!\n" +
                    "Person 2: Wow - Just 24 to go until a big round-number milestone!\n" +
                    "\n" +
                    "{{Title text: Thank you for making me feel less alone.}}",
            actual = model.transcript
        )
        assertEquals(
            message = "Expected alt to be 'Thank you for making me feel less alone'",
            expected = "Thank you for making me feel less alone.",
            actual = model.alt
        )
        assertEquals(
            message = "Expected img to be https://imgs.xkcd.com/comics/1000_comics.png",
            expected = "https://imgs.xkcd.com/comics/1000_comics.png",
            actual = model.img
        )
        assertEquals(
            message = "Expected title to be 1000 Comics",
            expected = "1000 Comics",
            actual = model.title
        )
        assertEquals(message = "Expected day to be 6", expected = "6", actual = model.day)
    }

    @Test
    fun requestComic404_viewModelDoesNotCrash() = runBlocking {
        comicViewModel.requestComicById(404)
        val captureId: Int? = comicViewModel.currentComicId.value
        assertEquals(
            message = "Expected id of mocked 404 comic to be 404",
            expected = 404,
            actual = captureId
        )
    }

    @Test
    fun requestComicsAboveBound_getComic404() = runBlocking {
        comicViewModel.requestComicById(10000000)
        val captureId: Int? = comicViewModel.currentComicId.value
        assertEquals(
            message = "Expected comic to set itself to 404 if exceeded bounds",
            expected = 404,
            actual = captureId
        )
    }

    @Test
    fun requestComicsBelowBound_getComic404() = runBlocking {
        comicViewModel.requestComicById(-10000000)
        val captureId: Int? = comicViewModel.currentComicId.value
        assertEquals(
            message = "Expected comic to set itself to 404 if below bounds",
            expected = 404,
            actual = captureId
        )
    }
}