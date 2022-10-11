package testee.it.e2e.core.pages

import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.ExpectedCondition


interface WaitForLoaded : Driver {

    /**
     * General wait for page source to be loaded.
     */
    fun waitForLoaded() {
        wait().until(ExpectedCondition { webDriver: WebDriver ->
            (webDriver as JavascriptExecutor).executeScript("return document.readyState") == "complete"
        } as ExpectedCondition<Boolean>)
    }
}