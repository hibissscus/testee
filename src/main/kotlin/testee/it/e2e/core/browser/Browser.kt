package testee.it.e2e.core.browser

/**
 * Enum for all possible [browser] which we want to use.
 */
enum class Browser(val browser: String) {
    FIREFOX("firefox"),
    CHROME("chrome"),
    EDGE("edge"),
    SAFARI("safari")
}