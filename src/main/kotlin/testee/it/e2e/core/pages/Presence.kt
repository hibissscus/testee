package testee.it.e2e.core.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions

interface Presence : Driver {

    /**
     *  Check if specific element by [locator] is presented on the [Page]
     */
    fun presence(locator: By): WebElement {
        return wait().until(ExpectedConditions.presenceOfElementLocated(locator))
    }

    /**
     *  Check if specific elements [WebElement] by [locator] are presented on the [Page]
     */
    fun presenceOfAll(locator: By): List<WebElement> {
        return wait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator))
    }

    /**
     *  Checking child WebElement as a part of parent element is present on the [Page] by [locator] and [childLocator]
     */
    fun presenceOfNestedElement(locator: By, childLocator: By): WebElement {
        return wait().until(ExpectedConditions.presenceOfNestedElementLocatedBy(locator, childLocator))
    }

    /**
     *  Checking child WebElements as a part of parent element are present on the [Page] by [locator] and [childLocator]
     */
    fun presenceOfNestedElements(locator: By, childLocator: By): List<WebElement> {
        return wait().until(ExpectedConditions.presenceOfNestedElementsLocatedBy(locator, childLocator))
    }

}