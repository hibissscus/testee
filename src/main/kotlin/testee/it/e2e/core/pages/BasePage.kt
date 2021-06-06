package testee.it.e2e.core.pages

import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.TimeoutException
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebDriverException
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable
import org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOf
import org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated
import org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf
import org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration
import java.time.LocalDate
import java.time.LocalTime

abstract class BasePage(private val driver: WebDriver) : Page {

    companion object {
        const val WAIT_MAX = 25L
        const val WAIT_MIN = 2L
    }

    protected val wait = WebDriverWait(driver, Duration.ofSeconds(WAIT_MAX))
    protected val tick = WebDriverWait(driver, Duration.ofSeconds(WAIT_MIN))

    protected val localDate: LocalDate = LocalDate.now()
    protected val localTime: LocalTime = LocalTime.now()

    /**
     * Initialization for all elements on the page marked with annotation
     * (e.x: @FindBy)
     */
    init {
        PageFactory.initElements(driver, this)
    }

    /**
     * View specific [Page]
     */
    override fun <T : Page> T.view(page: T): T = apply {
        navigate().isLoaded().isOpened()
    }

    /**
     * View specific [Page] and check the [title] on the [Page]
     */
    override fun <T : Page> T.view(page: T, title: String): T = apply {
        navigate().isLoaded().isOpened(title)
    }

    /**
     *  Click on it specific element [WebElement] on the [Page]
     */
    override fun <T : Page> T.click(element: WebElement): T = apply {
        wait.until(elementToBeClickable(element)).click()
    }

    /**
     * Perform [Actions] on the [Page]
     */
    override fun <T : Page> T.applyAction(action: Actions): T = apply {
        action.perform()
    }

    /**
     * Clear and send [text] to specific [WebElement] on this [Page]
     */
    fun <T : Page> T.sendText(element: WebElement, text: String): T = apply {
        wait.until(elementToBeClickable(element)).clear()
        wait.until(elementToBeClickable(element)).sendKeys(text)
    }

    /**
     * Clear and send [text] by specific [By] on this [Page]
     */
    fun <T : Page> T.sendText(by: By, text: String): T = apply {
        wait.until(elementToBeClickable(by)).clear()
        wait.until(elementToBeClickable(by)).sendKeys(text)
    }

    /**
     * Insert [text] in specific [element] on this [Page]
     */
    fun <T : Page> T.sendTextViaJavascript(element: WebElement, text: String): T = apply {
        val js = driver as JavascriptExecutor
        js.executeScript(
            "var elm = arguments[0], txt = arguments[1];\n" +
                    "  elm.value = txt;\n" +
                    "  elm.dispatchEvent(new Event('input'));", element, text
        )
    }

    /**
     * Apply java [script] on this [Page] with [varargs]
     */
    fun <T : Page> T.applyJavaScript(script: String, vararg varargs: Any): T = apply {
        val js = driver as JavascriptExecutor
        js.executeScript(script, varargs)
    }

    /**
     * Retry function with amount of [tries] for lambda action with catch of [TimeoutException]
     */
    protected fun retry(tries: Int, action: () -> Unit) {
        for (index in 1..tries + 1) {
            try {
                action()
                return
            } catch (e: TimeoutException) {
                if (index == tries + 1) throw e
            }
        }
    }

    /**
     * Quick check of presents of the specified element by [By] selector
     */
    protected fun isPresent(by: By): Boolean {
        setMinImplicitWait()
        try {
            tick.until(presenceOfElementLocated(by))
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
    protected fun isNotPresent(by: By): Boolean {
        setMinImplicitWait()
        try {
            driver.findElements(by)
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
    protected fun isVisible(by: By): Boolean {
        setMinImplicitWait()
        try {
            tick.until(visibilityOfElementLocated(by))
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
    protected fun isVisible(element: WebElement): Boolean {
        setMinImplicitWait()
        try {
            tick.until(visibilityOf(element))
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
    protected fun isInvisible(element: WebElement): Boolean {
        try {
            wait.until(invisibilityOf(element))
            println(element.isDisplayed)
        } catch (e: TimeoutException) {
            return false
        }
        return true
    }

    /**
     * Quick check of click ability [By] the locator of specified element
     */
    protected fun isClickable(by: By): Boolean {
        setMinImplicitWait()
        try {
            tick.until(elementToBeClickable(by))
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
    protected fun isClickable(element: WebElement): Boolean {
        setMinImplicitWait()
        try {
            tick.until(elementToBeClickable(element))
        } catch (e: TimeoutException) {
            return false
        } finally {
            setMaxImplicitWait()
        }
        return true
    }

    /**
     * Click on random [WebElement] and return its text.
     */
    protected fun clickOnRandomElement(by: By): String {
        driver.findElements(by)
            .random()
            .also {
                wait.until(elementToBeClickable(it)).click()
                return it.text
            }
    }

    /**
     * Set page load timeout in [milliseconds] for [driver]
     */
    fun setPageLoadTimeout(milliseconds: Long): BasePage = apply {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofMillis(milliseconds))
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(milliseconds))
        driver.manage().timeouts().scriptTimeout = Duration.ofMillis(milliseconds)
    }

    /**
     * Set implicit wait in [seconds] implicit wait for [driver]
     */
    fun setImplicitWait(seconds: Long = WAIT_MIN): BasePage = apply {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds))
    }

    /**
     * Set minimum [WAIT_MIN] implicit wait for [driver]
     */
    fun setMinImplicitWait(): BasePage = apply {
        setImplicitWait(WAIT_MIN)
    }

    /**
     * Set maximum [WAIT_MAX] implicit wait for [driver]
     */
    fun setMaxImplicitWait(): BasePage = apply {
        setImplicitWait(WAIT_MAX)
    }

    /**
     * General wait for page source to be loaded with [maxWaitMillis] timeout
     * and pool of [pollDelimiterMillis]
     */
    fun waitForLoaded(maxWaitMillis: Int = WAIT_MAX.toInt() * 1000, pollDelimiterMillis: Int = 500) {
        val startTime = System.currentTimeMillis()
        while (System.currentTimeMillis() < startTime + maxWaitMillis) {
            try {
                val prevState = driver.pageSource
                Thread.sleep(pollDelimiterMillis.toLong())
                if (prevState == driver.pageSource) {
                    return
                }
            } catch (e: WebDriverException) {
                Thread.sleep(pollDelimiterMillis.toLong())
            }
        }
    }
}