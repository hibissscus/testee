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
        PianoPage(driver)
            .open("https://virtualpiano.net")
            .also { takeScreenShot("open") }
            .loaded()
            .also { takeScreenShot("loaded") }
            .hideCookies()
            .also { takeScreenShot("hideCookies") }
            .freePlay(Note.SYM40, 50)
            .also { takeScreenShot("freePlay") }
            .waitForSeconds(10)
            .also { takeScreenShot("waitForSeconds") }
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