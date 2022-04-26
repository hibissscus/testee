package testee.it.e2e.core.pages

interface WaitForSeconds {

    /**
     * Waiting for amount of seconds on the [WaitForSeconds]
     *
     * @param timeoutInSeconds timeout in seconds for wait
     */
    fun <P : AbstractPage> P.waitForSeconds(timeoutInSeconds: Int): P = apply {
        Thread.sleep((timeoutInSeconds * 1000).toLong())
    }
}