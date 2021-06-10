package testee.it.e2e.core.pages

import com.google.common.base.Strings
import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions.attributeContains
import org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable
import org.openqa.selenium.support.ui.ExpectedConditions.refreshed

interface SendText : Driver {

    /**
     * Clear and send [text] to specific [WebElement] on this [Page] and [checkPresence] of the text
     */
    fun sendText(element: WebElement, text: String, checkPresence: Boolean = true) {
        wait().until(refreshed(elementToBeClickable(element))).clear()
        wait().until(refreshed(elementToBeClickable(element))).sendKeys(text)
        if (checkPresence && !Strings.isNullOrEmpty(text)) {
            wait().until(refreshed(attributeContains(element, "value", text)))
        }
    }

    /**
     * Clear and send [text] by specific [By] on this [Page] and [checkPresence] of the text
     */
    fun sendText(by: By, text: String, checkPresence: Boolean = true) {
        wait().until(refreshed(elementToBeClickable(by))).clear()
        wait().until(refreshed(elementToBeClickable(by))).sendKeys(text)
        if (checkPresence && !Strings.isNullOrEmpty(text)) {
            wait().until(refreshed(attributeContains(by, "value", text)))
        }
    }

    /**
     * Insert [text] in specific [element] on this [Page]
     */
    fun sendTextViaJavascript(element: WebElement, text: String) {
        val js = driver() as JavascriptExecutor
        js.executeScript(
            "var elm = arguments[0], txt = arguments[1];\n" +
                    "  elm.value = txt;\n" +
                    "  elm.dispatchEvent(new Event('input'));", element, text
        )
    }
}