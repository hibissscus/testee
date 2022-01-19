package testee.it.e2e.core.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions

interface Click : Driver, Visibility, Checks {
    /**
     *  Click on specific element [WebElement] on the [Page]
     */
    fun clickable(element: WebElement): WebElement {
        return wait().until(ExpectedConditions.elementToBeClickable(element))
    }

    /**
     *  Click on specific element by [By] on the [Page]
     */
    fun clickable(by: By): WebElement {
        return wait().until(ExpectedConditions.elementToBeClickable(by))
    }

    /**
     *  Click on specific element [WebElement] on the [Page]
     */
    fun click(element: WebElement) {
        wait().until(ExpectedConditions.elementToBeClickable(element)).click()
    }

    /**
     *  Click on specific element [By] on the [Page]
     */
    fun click(by: By) {
        wait().until(ExpectedConditions.elementToBeClickable(by)).click()
    }

    /**
     *  Click on specific element [WebElement] on the [Page] if it is visible
     */
    fun clickIfVisible(element: WebElement) {
        if (isVisible(element)) {
            click(element)
        }
    }

    /**
     * Click on random [WebElement] and return its text.
     */
    fun clickOnRandomElement(by: By): String {
        driver().findElements(by)
            .random()
            .also {
                wait().until(ExpectedConditions.elementToBeClickable(it)).click()
                return it.text
            }
    }
}