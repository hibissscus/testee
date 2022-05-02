package testee.it.e2e.core.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import java.util.regex.Pattern

interface TextToBe : Driver {

    /**
     *  Check if [text] is present in specific element [WebElement] on the [Page]
     */
    fun textToBe(element: WebElement, text: String): Boolean {
        return wait().until(ExpectedConditions.textToBePresentInElement(element, text))
    }

    /**
     *   Check if [text] is present in specific element by [By] on the [Page]
     */
    fun textToBe(by: By, text: String): Boolean {
        return wait().until(ExpectedConditions.textToBePresentInElementLocated(by, text))
    }

    /**
     *   An expectation for checking WebElement [By] has text with a value as a part of it on the [Page]
     */
    fun textMatch(by: By, pattern: Pattern): Boolean {
        return wait().until(ExpectedConditions.textMatches(by, pattern))
    }

}