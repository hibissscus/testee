package testee.it.e2e.core.pages

import org.openqa.selenium.TimeoutException

interface Retry {

    /**
     * Retry function with amount of [tries] for lambda action with catch of [TimeoutException]
     */
    fun retry(tries: Int, action: () -> Unit) {
        for (index in 1..tries + 1) {
            try {
                action()
                return
            } catch (e: TimeoutException) {
                println("retry")
                if (index == tries + 1) throw e
            }
        }
    }

    /**
     * Retry function with amount of [tries] for lambda action with catch of [Exception]
     */
    fun retryWithException(tries: Int, action: () -> Unit) {
        for (index in 1..tries + 1) {
            try {
                action()
                return
            } catch (e: Exception) {
                println("retry")
                if (index == tries + 1) throw e
            }
        }
    }
}