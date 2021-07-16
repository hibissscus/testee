package testee.it.tests.example.matryoshka

import org.testng.annotations.Test
import testee.it.e2e.core.test.TestBase
import testee.it.e2e.example.BasePage.Companion.open
import testee.it.e2e.example.matryoshka.Emoji
import testee.it.e2e.example.matryoshka.MatryoshkaPage


class MatryoshkaTest : TestBase(url = "https://pixlr.com/x/") {

    @Test
    fun `01 create different emoji`() {
        MatryoshkaPage(driver)
            .open(url)
            .closeAllModalDialogs()
            .pixlr()
            .saveImage(Emoji.E11)
    }

}