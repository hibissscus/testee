package testee.it.e2e.core.pages

import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.WebDriverWait

/**
 * [Driver] interface with main methods.
 */
interface Driver {
    fun driver(): WebDriver
    fun wait(): WebDriverWait
    fun tick(): WebDriverWait
    fun waitMin(): Long
    fun waitMax(): Long
}