package testee.it.e2e.core.pages

import org.openqa.selenium.By
import org.openqa.selenium.TimeoutException
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable
import org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOf
import org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated
import org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf
import org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated
import java.util.concurrent.TimeUnit

interface Checks : Driver {
    /**
     * Set page load timeout in [milliseconds] for [driver]
     */
    fun setPageLoadTimeout(milliseconds: Long) {
        driver().manage().timeouts().pageLoadTimeout(milliseconds, TimeUnit.NANOSECONDS)
        driver().manage().timeouts().implicitlyWait(milliseconds, TimeUnit.NANOSECONDS)
        driver().manage().timeouts().setScriptTimeout(milliseconds, TimeUnit.NANOSECONDS)
    }

    /**
     * Set implicit wait in [seconds] implicit wait for [driver]
     */
    fun setImplicitWait(seconds: Long = waitMax()) {
        driver().manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS)
    }

    /**
     * Set minimum implicit wait for [driver]
     */
    fun setMinImplicitWait() {
        setImplicitWait(waitMin())
    }

    /**
     * Set maximum implicit wait for [driver]
     */
    fun setMaxImplicitWait() {
        setImplicitWait(waitMax())
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