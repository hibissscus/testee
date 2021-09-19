package testee.it.tests.example.piano

import org.testng.annotations.Test
import testee.it.e2e.core.browser.Browser
import testee.it.e2e.core.test.TestBase
import testee.it.e2e.example.BasePage.Companion.open
import testee.it.e2e.example.BasePage.Companion.waitForSeconds
import testee.it.e2e.example.piano.Note
import testee.it.e2e.example.piano.PianoPage


class PianoTest : TestBase(url = "https://virtualpiano.net", browser = Browser.CHROME) {

    @Test
    fun `01 piano play with Mentor`() {
        PianoPage(driver)
            .open("$url/?song-post-15552")
            .acceptAllCookies()
            .hideAdvertisement()
            .waitForSeconds(2)
            .mentorPlay()
            .waitForSeconds(10)
    }

    @Test
    fun `02 piano play`() {
        PianoPage(driver)
            .open("https://virtualpiano.net")
            .acceptAllCookies()
            .hideAdvertisement()
            .waitForSeconds(2)
            .freePlay(Note.SYM40, 50)
            .waitForSeconds(10)
    }

}