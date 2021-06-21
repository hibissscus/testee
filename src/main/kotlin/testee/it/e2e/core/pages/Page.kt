package testee.it.e2e.core.pages

/**
 * Main [Page] interface with default methods for page manipulation.
 * All tests pages should implement this interface.
 */
interface Page : ApplyAction, Checks, Click, Retry, SelectDropdownOption, SendText, WaitForLoaded, WaitForSeconds {

    /**
     * Check that on [Page] are no loading process
     */
    fun isLoaded(): Page

    /**
     * Check that this [Page] is opened
     */
    fun isOpened(s: String = ""): Page

    /**
     * Navigate to [url] of the [Page]
     */
    fun <T : Page> navigate(page: T, url: String = ""): T

    /**
     * View specific [Page]
     */
    fun <T : Page> view(page: T): T

    /**
     * View specific [Page] and check the [title] on the [Page]
     */
    fun <T : Page> view(page: T, title: String): T
}