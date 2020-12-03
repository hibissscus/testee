package testee.it.e2e.core.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions

/**
 * Main [Page] interface with default methods for page manipulation.
 * All out tests pages should implement this interface.
 */
interface Page : WaitForSeconds, SelectDropdownOption {

    /**
     * Navigate to this [Page] is opened
     */
    fun navigate(url: String = ""): Page

    /**
     * Check that this [Page] is opened
     */
    fun isOpened(s: String = ""): Page

    /**
     * Check that on [Page] are no loading process
     */
    fun isLoaded(): Page

    /**
     * Apply particular [Actions] chain for specific [Page]
     */
    fun <T : Page> T.applyAction(action: Actions): T

    /**
     * Click on [WebElement] on the [Page]
     */
    fun <T : Page> T.click(element: WebElement): T

    /**
     * View specific [Page]
     */
    fun <T : Page> T.view(page: T): T

    /**
     * View specific [Page] and check the [title] on the [Page]
     */
    fun <T : Page> T.view(page: T, title: String): T

    /**
     * [WaitForSeconds]
     */
    override fun waitForSeconds(timeoutInSeconds: Int): Page = apply {
        super.waitForSeconds(timeoutInSeconds)
    }

    /**
     * [SelectDropdownOption]
     */
    override fun selectDropdownOption(driver: WebDriver, by: By, value: String): Page = apply {
        super.selectDropdownOption(driver, by, value)
    }

    /**
     * [SelectDropdownOption]
     */
    override fun selectDropdownOption(element: WebElement, value: String): Page = apply {
        super.selectDropdownOption(element, value)
    }
}