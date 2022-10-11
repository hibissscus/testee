package testee.it.e2e.core.pages

import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.support.ui.ExpectedCondition


interface WaitForLoaded : Driver {

    /**
     * General wait for page source to be loaded.
     */
    fun waitForLoaded() {
        wait().until(ExpectedCondition {
            ((driver() as JavascriptExecutor).executeScript("return document.readyState")).equals("complete")
        })
    }
}