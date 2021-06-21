package testee.it.e2e.core.test

import org.testng.IRetryAnalyzer
import org.testng.ITestResult

/**
 * ... RETRY ANALYZER ...
 */
class RetryAnalyzer : IRetryAnalyzer {
    companion object {
        val maxRetries = 1
    }

    var retries = 0
    override fun retry(result: ITestResult): Boolean {
        retries++
        if (retries <= maxRetries && !result.isSuccess) {
            println("Retry test: " + result.method + ", " + retries + " out of " + maxRetries)
            return true
        }
        return false
    }
}