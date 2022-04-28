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