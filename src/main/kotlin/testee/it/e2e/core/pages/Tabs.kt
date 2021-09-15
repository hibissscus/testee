package testee.it.e2e.core.pages

interface Tabs : Driver, ApplyAction, WaitForLoaded {
    /**
     * Create new browser tab
     */
    fun createNewTab(url: String) {
        val tabs = ArrayList(driver().windowHandles)
        applyJavaScript("window.open('$url', '_blank');")
        waitForLoaded()
        switchToTab(tabs.size)
    }

    /**
     * Switch between browser tabs
     */
    fun switchToTab(tabNumber: Int) {
        val tabs = ArrayList(driver().windowHandles)
        if (tabNumber < tabs.size) {
            driver().switchTo().window(tabs[tabNumber])
        }
    }
}