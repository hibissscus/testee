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

    /**
     * Navigate to [url] of for this [Page]
     */
    fun <P : AbstractPage> P.navigate(url: String): P = apply {
        driver().navigate().to(url)
        view(this)
    }

    /**
     * Navigate to new [Page] by [url]
     */
    fun <P : AbstractPage> P.navigate(page: P, url: String): P = apply {
        driver().navigate().to(url)
        view(page)
    }

    /**
     * View specific [Page]
     */
    fun <P : AbstractPage> P.view(page: P): P = page.apply {
        isLoaded().isOpened()
    }

    /**
     * View specific [Page] and check the [title] on the [Page]
     */
    fun <P : AbstractPage> P.view(page: P, title: String): P = page.apply {
        isLoaded().isOpened(title)
    }
}