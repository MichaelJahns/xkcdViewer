package com.leyline.xkcd

import com.leyline.xkcd.util.ComicApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitTest {
    private lateinit var retrofit: Retrofit
    private lateinit var service: ComicApi

    @Before
    fun setup() {
        retrofit = Retrofit.Builder()
            .baseUrl(("https://xkcd.com"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(ComicApi::class.java)
    }

    @Test
    fun getComicById_Number1_shouldBeFirstComic() = runBlocking<Unit> {
        val response = service.getComicByIdAsync(1)

        assertTrue(
            "First comic was expected to have been released in January",
            response.month == "1"
        )
        assertTrue("First comic was expected to have the first id", response.num == 1)
        assertTrue("First comic was expected to have an empty link", response.link == "")
        assertTrue("First comic was expected to have release in 2006", response.year == "2006")
        assertTrue("First comic was expected to have an empty news", response.news == "")
        assertTrue(
            "First comic was expected to have its safe_title as follows 'Barrel - Part 1'",
            response.safeTitle == "Barrel - Part 1"
        )
        assertTrue(
            "First comic was expected to have a transcript as follows '[[A boy sits in a barrel which is floating in an ocean.]]\\nBoy: I wonder where I'll float next?\\n[[The barrel drifts into the distance. Nothing else can be seen.]]\\n{{Alt: Don't we all.}}\"'",
            response.transcript == "[[A boy sits in a barrel which is floating in an ocean.]]\nBoy: I wonder where I'll float next?\n[[The barrel drifts into the distance. Nothing else can be seen.]]\n{{Alt: Don't we all.}}"
        )
        assertTrue(
            "First comic was expected to have an alt text as follows 'Don't we all.'",
            response.alt == "Don't we all."
        )
        assertTrue(
            "First comic was expected to have its image at address 'https://imgs.xkcd.com/comics/barrel_cropped_(1).jpg'",
            response.img == "https://imgs.xkcd.com/comics/barrel_cropped_(1).jpg"
        )
        assertTrue(
            "First comic was expected to have its title as follows 'Barrel - Part 1'",
            response.title == "Barrel - Part 1"
        )
        assertTrue(
            "First comic was expected to have released on the first of the month",
            response.day == "1"
        )
    }

    @Test
    fun getComicById_Number100_shouldBeHundredthComic() = runBlocking<Unit> {
        val response = service.getComicByIdAsync(100)

        assertTrue(
            "Hundredth comic was expected to have been released in May",
            response.month == "5"
        )
        assertTrue("Hundredth comic was expected to have the hundredth id", response.num == 100)
        assertTrue("Hundredth comic was expected to have an empty link", response.link == "")
        assertTrue("Hundredth comic was expected to have release in 2006", response.year == "2006")
        assertTrue("Hundredth comic was expected to have an empty news", response.news == "")
        assertTrue(
            "Hundredth comic was expected to have its safe_title as follows 'Family Circus'",
            response.safeTitle == "Family Circus"
        )
        assertTrue(
            "Hundredth comic was expected to have a transcript as follow '[[Picture shows a pathway winding through trees to a sink inside a house, out to some swings and back to ths sink, out to a ball and back to the sink...]]\\nCaption: Jeffy's ongoing struggle with obsessive-compulsive disorder\\n{{alt text: This was my friend David's idea}}'",
            response.transcript == "[[Picture shows a pathway winding through trees to a sink inside a house, out to some swings and back to ths sink, out to a ball and back to the sink...]]\nCaption: Jeffy's ongoing struggle with obsessive-compulsive disorder\n{{alt text: This was my friend David's idea}}"
        )
        assertTrue(
            "Hundredth comic was expected to have an alt text as follows 'This was my friend David's idea'",
            response.alt == "This was my friend David's idea"
        )
        assertTrue(
            "Hundredth comic was expected to have its image at address 'https://imgs.xkcd.com/comics/family_circus.jpg',",
            response.img == "https://imgs.xkcd.com/comics/family_circus.jpg"
        )
        assertTrue(
            "Hundredth comic was expected to have its title as follows 'Family Circus'",
            response.title == "Family Circus"
        )
        assertTrue(
            "Hundredth comic was expected to have released on the tenth of the month",
            response.day == "10"
        )
    }

    @Test
    fun getComicById_Number1000_shouldBeThousandthComic() = runBlocking<Unit> {
        val response = service.getComicByIdAsync(1000)
        assertTrue(
            "Thousandth comic was expected to have been released in January",
            response.month == "1"
        )
        assertTrue("Thousandth comic was expected to have the first id", response.num == 1000)
        assertTrue(
            "Thousandth comic was expected to have a link as follows 'https://xkcd.com/1000/large/'",
            response.link == "https://xkcd.com/1000/large/"
        )
        assertTrue("Thousandth comic was expected to have release in 2006", response.year == "2012")
        assertTrue("Thousandth comic was expected to have an empty news", response.news == "")
        assertTrue(
            "Thousandth comic was expected to have its safe_title as follows '1000 Comics'",
            response.safeTitle == "1000 Comics"
        )
        assertTrue(
            "Thousandth comic was expected to have a transcript as follow '[[1000 characters, numerous of which have appeared previously in other comics, are arranged to create the number \\\"1000\\\". Two more people stand in the foreground commenting on the formation]]\\n\\nPerson 1: WOOOO!\\nPerson 2: Wow - Just 24 to go until a big round-number milestone!\\n\\n{{Title text: Thank you for making me feel less alone.}}",
            response.transcript == "[[1000 characters, numerous of which have appeared previously in other comics, are arranged to create the number \"1000\". Two more people stand in the foreground commenting on the formation]]\n\nPerson 1: WOOOO!\nPerson 2: Wow - Just 24 to go until a big round-number milestone!\n\n{{Title text: Thank you for making me feel less alone.}}"
        )
        assertTrue(
            "Thousandth comic was expected to have an alt text as follows 'Thank you for making me feel less alone.'",
            response.alt == "Thank you for making me feel less alone."
        )
        assertTrue(
            "Thousandth comic was expected to have its image at address '',",
            response.img == "https://imgs.xkcd.com/comics/1000_comics.png"
        )
        assertTrue(
            "Thousandth comic was expected to have its title as follows '1000 Comics'",
            response.title == "1000 Comics"
        )
        assertTrue(
            "Thousandth comic was expected to have released on the sixth of the month",
            response.day == "6"
        )
    }
}