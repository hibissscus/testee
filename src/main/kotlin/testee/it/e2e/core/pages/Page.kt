package testee.it.e2e.core.pages

/**
 * Main [Page] interface with default methods for page manipulation.
 */
interface Page :
    ApplyAction,
    Attribute,
    Checks,
    Click,
    Driver,
    Frame,
    Number,
    Presence,
    Retry,
    SelectDropdownOption,
    SendText,
    Screenshot,
    Tabs,
    Text,
    Timeouts,
    Visibility,
    WaitForLoaded,
    WaitForSeconds