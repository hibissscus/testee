package testee.it.e2e.core.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions

interface Visibility : Driver {

    /**
     *  Check if specific element [WebElement] is visible on the [Page]
     */
    fun visible(element: WebElement): WebElement {
        return wait().until(ExpectedConditions.visibilityOf(element))
    }

    /**
     *   Check if specific element by [By] is visible on the [Page]
     */
    fun visible(by: By): WebElement {
        return wait().until(ExpectedConditions.visibilityOfElementLocated(by))
    }

    /**
     * Checking child [WebElement]s as a part of parent element are visible by [childLocator]
     */
    fun visibilityOfNestedElementsLocatedBy(parentLocator: By, childLocator: By): List<WebElement> {
        return wait().until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(parentLocator, childLocator))
    }

    /**
     * Checking child [WebElement]s as a part of parent [WebElement] are visible by [childLocator]
     */
    fun visibilityOfNestedElementsLocatedBy(parentLocator: WebElement, childLocator: By): List<WebElement> {
        return wait().until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(parentLocator, childLocator))
    }

    /**
     * Checking that all elements present on the web [Page] that match the locator are visible.
     * Visibility means that the elements are not only displayed but also have a height and width that is greater than 0.
     */
    fun visibilityOfAllElements(elements: List<WebElement>): List<WebElement> {
        return wait().until(ExpectedConditions.visibilityOfAllElements(elements))
    }

    /**
    checking that all elements present on the web [Page] that match the locator are visible.
    Visibility means that the elements are not only displayed but also have a height and width that is greater than 0.
     */
    fun visibilityOfAllElementsLocatedBy(by: By): List<WebElement> {
        return wait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by))
    }

    /**
     *  Check if specific element [WebElement] is invisible on the [Page]
     */
    fun invisible(element: WebElement): Boolean {
        return wait().until(ExpectedConditions.invisibilityOf(element))
    }

    /**
     *   Check if specific element by [By] is invisible on the [Page]
     */
    fun invisible(by: By): Boolean {
        return wait().until(ExpectedConditions.invisibilityOfElementLocated(by))
    }

}