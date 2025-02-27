package testee.it.tests.example.piano

import org.testng.annotations.Test
import testee.it.e2e.core.browser.Browser
import testee.it.e2e.core.test.TestBase
import testee.it.e2e.example.BasePage.Companion.loaded
import testee.it.e2e.example.BasePage.Companion.open
import testee.it.e2e.example.BasePage.Companion.waitForSeconds
import testee.it.e2e.example.piano.Note
import testee.it.e2e.example.piano.PianoPage


class PianoTest : TestBase(url = "https://virtualpiano.net", browser = Browser.CHROME) {

    @Test
    fun `01 piano play`() {
        PianoPage(driver, screenshot = ::takeScreenShot)
            .open("https://virtualpiano.net")
            .loaded()
            .apply { takeScreenShot("loaded!") }
            .hideCookies()
            .freePlay(Note.SYM40, 50)
            .waitForSeconds(10)
    }

    @Test
    fun `02 piano play with Mentor`() {
        PianoPage(driver)
            .open("$url/?song-post-" + Note.FLY.id)
            .loaded()
            .hideCookies()
            .mentorPlay()
            .waitForSeconds(10)
    }

}