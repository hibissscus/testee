package testee.it.tests.example.dino

import org.testng.annotations.Test
import testee.it.e2e.core.browser.Browser
import testee.it.e2e.core.test.TestBase
import testee.it.e2e.example.BasePage.Companion.loaded
import testee.it.e2e.example.BasePage.Companion.waitForSeconds
import testee.it.e2e.example.dino.DinoPage


class DinoTest : TestBase(url = "chrome://dino/", browser = Browser.CHROME) {

    @Test
    fun `01 dino run`() {
        DinoPage(driver)
            .loaded()
            .cheat()
            .start()
            .waitForSeconds(10)
    }

}