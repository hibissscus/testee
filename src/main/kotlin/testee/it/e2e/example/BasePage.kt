package testee.it.e2e.example

import org.openqa.selenium.WebDriver
import testee.it.e2e.core.pages.AbstractPage
import testee.it.e2e.core.pages.Page
import testee.it.e2e.core.pages.WaitForSeconds

/**
 * Main [BasePage] which implement [AbstractPage] interface.
 * All tests pages should extend this [BasePage]
 * Those methods can be overwritten
 */
abstract class BasePage(driver: WebDriver) : AbstractPage(driver) {

    abstract fun opened(): BasePage

    companion object {
        /**
         * General wait for page source to be loaded.
         */
        fun <P : BasePage> P.loaded(): P {
            waitForLoaded()
            return this
        }

        /**
         * Waiting for amount of seconds on the [WaitForSeconds]
         *
         * @param timeoutInSeconds timeout in seconds for wait
         */
        fun <P : BasePage> P.waitForSeconds(timeoutInSeconds: Int): P {
            return waitForSeconds(timeoutInSeconds)
        }

        /**
         * Navigate to [url] of for this [Page]
         * Check that on [Page] are no loading process
         */
        fun <P : BasePage> P.open(url: String): P {
            driver().navigate().to(url)
            return view(this)
        }

        /**
         * Navigate to new [Page] by [url]
         * Check that on [Page] are no loading process
         */
        fun <P : BasePage, T : BasePage> P.open(page: T, url: String): T {
            driver().navigate().to(url)
            return view(page)
        }

        /**
         * View specific [Page] and check the [title] on the [Page]
         */
        fun <T : BasePage> view(page: T): T {
            (page).loaded().opened()
            return page
        }

        /**
         * Create new tab for this [Page]
         */
        fun <P : BasePage> P.newTab(url: String): P {
            createNewTab(url)
            this.loaded().opened()
            return this
        }

        /**
         * Switch between browser tabs for this [Page]
         */
        fun <P : BasePage> P.switchTab(tabNumber: Int): P {
            switchToTab(tabNumber)
            return this
        }
    }
}