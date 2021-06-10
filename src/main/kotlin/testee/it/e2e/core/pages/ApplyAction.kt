package testee.it.e2e.core.pages

import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.interactions.Actions

interface ApplyAction : Driver {
    /**
     * Perform [Actions] on the [Page]
     */
    fun applyAction(action: Actions) {
        action.perform()
    }

    /**
     * Apply java [script] on this [Page] with [varargs]
     */
    fun applyJavaScript(script: String, vararg varargs: Any) {
        val js = driver() as JavascriptExecutor
        js.executeScript(script, varargs)
    }
}