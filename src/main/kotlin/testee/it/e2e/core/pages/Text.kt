package testee.it.e2e.core.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import java.util.regex.Pattern

interface Text : Driver {

    /**
     *  Check if [text] is present in specific element [WebElement] on the [Page]
     */
    fun textToBe(element: WebElement, text: String): Boolean {
        return wait().until(ExpectedConditions.textToBePresentInElement(element, text))
    }

    /**
     *   Check if [text] is present in specific element by [locator] on the [Page]
     */
    fun textToBe(locator: By, text: String): Boolean {
        return wait().until(ExpectedConditions.textToBePresentInElementLocated(locator, text))
    }

    /**
     *   Check if [text] is present in specific element value attribute on the [Page]
     */
    fun textToBePresentInElementValue(element: WebElement, text: String): Boolean {
        return wait().until(ExpectedConditions.textToBePresentInElementValue(element, text))
    }

    /**
     *   Check if [text] is present in specific element value attribute on the [Page]
     */
    fun textToBePresentInElementValue(locator: By, text: String): Boolean {
        return wait().until(ExpectedConditions.textToBePresentInElementValue(locator, text))
    }

    /**
     *   Checking WebElement by [locator] has text with a value as a part of it on the [Page]
     */
    fun textMatches(locator: By, pattern: Pattern): Boolean {
        return wait().until(ExpectedConditions.textMatches(locator, pattern))
    }

}