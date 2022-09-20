package testee.it.e2e.core.pages

import org.openqa.selenium.By
import org.openqa.selenium.TimeoutException
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable
import org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOf
import org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated
import org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf
import org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated

interface Checks : Driver, Timeouts {


    /**
     * Checking the URL of the current [Page] to be a specific [url]
     */
    fun urlToBe(url: String) {
        wait().until(ExpectedConditions.urlToBe(url))
    }

    /**
     * Checking the URL of the current [Page] to contain specific [url] text
     */
    fun urlContains(url: String) {
        wait().until(ExpectedConditions.urlContains(url))
    }

    /**
     * Checking the URL of the current [Page] to match a specific regular expression [regex]
     */
    fun urlMatches(regex: String) {
        wait().until(ExpectedConditions.urlMatches(regex))
    }

    /**
     * Checking the [title] of a [Page]
     */
    fun titleIs(title: String) {
        wait().until(ExpectedConditions.titleIs(title))
    }

    /**
     * Checking that the title contains a case-sensitive substring on the [Page]
     */
    fun titleContains(title: String) {
        wait().until(ExpectedConditions.titleContains(title))
    }

    /**
     * Quick check if URL contains string [url]
     */
    fun isUrl(url: String): Boolean {
        setMinImplicitWait()
        try {
            tick().until(ExpectedConditions.urlContains(url))
        } catch (e: TimeoutException) {
            return false
        } finally {
            setMaxImplicitWait()
        }
        return true
    }

    /**
     * Quick check of presents of the specified element by [By] selector
     */
    fun isPresent(by: By): Boolean {
        setMinImplicitWait()
        try {
            tick().until(presenceOfElementLocated(by))
        } catch (e: TimeoutException) {
            return false
        } finally {
            setMaxImplicitWait()
        }
        return true
    }

    /**
     * Quick check of presents of the specified element by [By] selector
     */
    fun isNotPresent(by: By): Boolean {
        setMinImplicitWait()
        try {
            driver().findElements(by)
        } catch (e: TimeoutException) {
            return true
        } finally {
            setMaxImplicitWait()
        }
        return false
    }

    /**
     * Quick check of visibility of the specified element by [By] selector
     */
    fun isVisible(by: By): Boolean {
        setMinImplicitWait()
        try {
            tick().until(visibilityOfElementLocated(by))
        } catch (e: TimeoutException) {
            return false
        } finally {
            setMaxImplicitWait()
        }
        return true
    }

    /**
     * Quick check of visibility of the specified [WebElement]
     */
    fun isVisible(element: WebElement): Boolean {
        setMinImplicitWait()
        try {
            tick().until(visibilityOf(element))
        } catch (e: TimeoutException) {
            return false
        } finally {
            setMaxImplicitWait()
        }
        return true
    }

    /**
     * Check of invisibility of the specified [WebElement]
     */
    fun isInvisible(element: WebElement): Boolean {
        try {
            wait().until(invisibilityOf(element))
            println(element.isDisplayed)
        } catch (e: TimeoutException) {
            return false
        }
        return true
    }

    /**
     * Quick check of click ability [By] the locator of specified element
     */
    fun isClickable(by: By): Boolean {
        setMinImplicitWait()
        try {
            tick().until(elementToBeClickable(by))
        } catch (e: TimeoutException) {
            return false
        } finally {
            setMaxImplicitWait()
        }
        return true
    }

    /**
     * Quick check of click ability of the specified [WebElement]
     */
    fun isClickable(element: WebElement): Boolean {
        setMinImplicitWait()
        try {
            tick().until(elementToBeClickable(element))
        } catch (e: TimeoutException) {
            return false
        } finally {
            setMaxImplicitWait()
        }
        return true
    }

}