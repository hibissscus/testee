package testee.it.e2e.example

import org.openqa.selenium.WebDriver
import testee.it.e2e.core.pages.AbstractPage

/**
 * Main [BasePage] which implement [AbstractPage] interface.
 * All tests pages should extend this [BasePage]
 * Those methods can be overridden
 */
abstract class BasePage(driver: WebDriver) : AbstractPage(driver) {

    companion object {
        fun <P : BasePage> P.open(url: String): P {
            return open(url)
        }

        fun <P : BasePage> P.open(page: P, url: String): P {
            return open(page, url)
        }

        fun <P : BasePage> P.waitForSeconds(timeoutInSeconds: Int): P {
            return waitForSeconds(timeoutInSeconds)
        }
    }
}