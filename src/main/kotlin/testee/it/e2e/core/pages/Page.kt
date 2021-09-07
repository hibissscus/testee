package testee.it.e2e.core.pages

/**
 * Main [Page] interface with default methods for page manipulation.
 */
interface Page : ApplyAction, Checks, Click, SelectDropdownOption, SendText, WaitForLoaded, Retry, WaitForSeconds {
}