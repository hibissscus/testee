package testee.it.tests.example.dino

import org.testng.annotations.Test
import testee.it.e2e.core.test.TestBase
import testee.it.e2e.example.dino.DinoPage


class DinoTest : TestBase(url = "chrome://dino/") {

    @Test
    fun `01 dino run`() {
        DinoPage(driver)
            .navigate()
            .cheat()
            .start()
            .waitForSeconds(3600)
    }

}