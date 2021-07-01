package testee.it.e2e.core.pages

/**
 * Main [Page] interface with default methods for page manipulation.
 */
interface Page : ApplyAction, Checks, Click, Retry, SelectDropdownOption, SendText, WaitForLoaded, WaitForSeconds {

    /**
     * Check that this [Page] is opened
     */
    fun <P : AbstractPage> P.isOpened(s: String = ""): P

    /**
     * Check that on [Page] are no loading process
     */
    fun <P : AbstractPage> P.isLoaded(): P = apply {
        waitForLoaded()
    }
}