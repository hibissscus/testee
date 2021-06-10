package testee.it.e2e.core.pages

interface WaitForSeconds {
    /**
     * Waiting for amount of seconds on the [WaitForSeconds]
     *
     * @param timeoutInSeconds timeout in seconds for wait
     */
    fun waitForSeconds(timeoutInSeconds: Int) {
        Thread.sleep((timeoutInSeconds * 1000).toLong())
    }
}