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
        return 2
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
}