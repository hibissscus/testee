package testee.it.e2e.core.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions

interface Click : Driver, Visibility, Checks {

    /**
     *  Check if specific element [WebElement] can be clicked on the [Page]
     *
     *  @param refreshed wrapper for a condition, which allows for elements to update by redrawing.
     *  This works around the problem of conditions which have two parts: find an element and then check for some condition on it.
     *  For these conditions it is possible that an element is located and then subsequently it is redrawn on the client.
     *  When this happens a StaleElementReferenceException is thrown when the second part of the condition is checked.
     */
    fun clickable(element: WebElement, refreshed: Boolean = false): WebElement {
        return wait().until(
            if (refreshed) ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(element))
            else ExpectedConditions.elementToBeClickable(element)
        )
    }

    /**
     *  Check if specific element by [By] can be clicked on the [Page]
     *
     *  @param refreshed wrapper for a condition, which allows for elements to update by redrawing.
     *  This works around the problem of conditions which have two parts: find an element and then check for some condition on it.
     *  For these conditions it is possible that an element is located and then subsequently it is redrawn on the client.
     *  When this happens a StaleElementReferenceException is thrown when the second part of the condition is checked.
     */
    fun clickable(by: By, refreshed: Boolean = false): WebElement {
        return wait().until(
            if (refreshed) ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(by))
            else ExpectedConditions.elementToBeClickable(by)
        )
    }

    /**
     *  Click on specific element [WebElement] on the [Page]
     */
    fun click(element: WebElement, refreshed: Boolean = false) {
        clickable(element, refreshed).click()
    }

    /**
     *  Click on specific element [By] on the [Page]
     */
    fun click(by: By, refreshed: Boolean = false) {
        clickable(by, refreshed).click()
    }

    /**
     *  Click on specific element [WebElement] on the [Page] if it is visible
     */
    fun clickIfVisible(element: WebElement) {
        if (isVisible(element)) {
            return click(element)
        }
    }

    /**
     * Click on random [WebElement] and return its text.
     */
    fun clickOnRandomElement(by: By): String {
        driver().findElements(by).random().also {
            wait().until(ExpectedConditions.elementToBeClickable(it)).click()
            return it.text
        }
    }
}