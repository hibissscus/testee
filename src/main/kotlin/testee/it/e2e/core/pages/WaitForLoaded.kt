package testee.it.e2e.core.pages

import org.openqa.selenium.WebDriverException

interface WaitForLoaded : Driver {

    /**
     * General wait for page source to be loaded with [maxWaitMillis] timeout
     * and pool of [pollDelimiterMillis]
     */
    fun waitForLoaded(maxWaitMillis: Int = waitMax().toInt() * 1000, pollDelimiterMillis: Int = 500) {
        val startTime = System.currentTimeMillis()
        while (System.currentTimeMillis() < startTime + maxWaitMillis) {
            try {
                val prevState = driver().pageSource
                Thread.sleep(pollDelimiterMillis.toLong())
                if (prevState == driver().pageSource) {
                    return
                }
            } catch (e: WebDriverException) {
                Thread.sleep(pollDelimiterMillis.toLong())
            }
        }
    }
}