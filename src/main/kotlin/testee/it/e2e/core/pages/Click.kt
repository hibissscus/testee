package testee.it.e2e.core.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions

interface Click : Driver {
    /**
     *  Click on it specific element [WebElement] on the [Page]
     */
    fun clickable(element: WebElement): WebElement {
        return wait().until(ExpectedConditions.elementToBeClickable(element))
    }

    /**
     *  Click on it specific element by [By] on the [Page]
     */
    fun clickable(element: By): WebElement {
        return wait().until(ExpectedConditions.elementToBeClickable(element))
    }

    /**
     *  Click on it specific element [WebElement] on the [Page]
     */
    fun click(element: WebElement) {
        wait().until(ExpectedConditions.elementToBeClickable(element)).click()
    }

    /**
     *  Click on it specific element [By] on the [Page]
     */
    fun click(element: By) {
        wait().until(ExpectedConditions.elementToBeClickable(element)).click()
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