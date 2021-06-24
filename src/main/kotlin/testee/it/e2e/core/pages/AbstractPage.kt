package testee.it.e2e.core.pages

import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

/**
 * Main [AbstractPage] which implement [Page] interface.
 * All tests pages should extend this [AbstractPage]
 * Those methods can be overridden
 */
abstract class AbstractPage(protected val driver: WebDriver) : Page {

    /**
     * Initialization for all elements on the page marked with annotation
     * (e.x: @FindBy)
     */
    init {
        PageFactory.initElements(driver, this)
    }

    override fun driver(): WebDriver {
        return driver
    }

    override fun waitMin(): Long {
        return 2L
    }

    override fun waitMax(): Long {
        return 25
    }

    override fun wait(): WebDriverWait {
        return WebDriverWait(driver, Duration.ofSeconds(waitMax()))
    }

    override fun tick(): WebDriverWait {
        return WebDriverWait(driver, Duration.ofSeconds(waitMin()))
    }

    override fun <T : Page> navigate(page: T, url: String): T = page.apply {
        driver().navigate().to(url)
        view(page)
    }

    /**
     * Check that on [Page] are no loading process.
     */
    override fun isLoaded(): AbstractPage = apply {
        waitForLoaded()
    }


    /**
     * View specific [Page]
     */
    override fun <T : Page> view(page: T): T = page.apply {
        isLoaded().isOpened()
    }


    /**
     * View specific [Page] and check the [title] on the [Page]
     */
    override fun <T : Page> view(page: T, title: String): T = page.apply {
        isLoaded().isOpened(title)
    }
}