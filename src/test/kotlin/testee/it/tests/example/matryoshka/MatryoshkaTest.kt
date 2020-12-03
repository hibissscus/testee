package testee.it.tests.example.matryoshka

import com.bitminer.services.matryoshka.Emoji
import org.testng.annotations.Test
import testee.it.e2e.example.matryoshka.MatryoshkaPage
import testee.it.tests.TestBase


class MatryoshkaTest : TestBase() {

    @Test
    fun `01 create different emoji`() {
        MatryoshkaPage(driver)
            .navigate()
            .closeAllModalDialogs()
            .pixlr()
            .saveImage(Emoji.E11)
    }

}