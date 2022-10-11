package testee.it.tests.example.minion

import org.testng.annotations.Test
import testee.it.e2e.core.browser.Browser
import testee.it.e2e.core.test.TestBase
import testee.it.e2e.example.BasePage.Companion.loaded
import testee.it.e2e.example.BasePage.Companion.open
import testee.it.e2e.example.minion.MinionPage


class MinionTest : TestBase(url = "https://pixlr.com/x/", browser = Browser.CHROME) {

    private val imageUrl = "https://i.ibb.co/wLxpY5F/Minion.png"

    @Test
    fun `01 create different emoji`() {
        MinionPage(driver)
            .open(url)
            .loaded()
            .closeAllModalDialogs()
            .openNewImageFromUrl(imageUrl)
            .pixlrPreset()
            .emoji(1)
    }

}