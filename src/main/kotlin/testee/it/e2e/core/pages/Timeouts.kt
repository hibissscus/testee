package testee.it.e2e.core.pages

import java.time.Duration

interface Timeouts : Driver {

    /**
     * Set page load timeout in [milliseconds] for [driver]
     */
    fun setPageLoadTimeout(milliseconds: Long) {
        driver().manage().timeouts().pageLoadTimeout(Duration.ofMillis(milliseconds))
        driver().manage().timeouts().implicitlyWait(Duration.ofMillis(milliseconds))
        driver().manage().timeouts().scriptTimeout(Duration.ofMillis(milliseconds))
    }

    /**
     * Set implicit wait in [seconds] implicit wait for [driver]
     */
    fun setImplicitWait(seconds: Long = waitMax()) {
        driver().manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds))
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
}