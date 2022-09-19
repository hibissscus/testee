package testee.it.e2e.core.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions

interface Frame : Driver {

    /**
     * Checking whether the given frame by [element] is available to switch to.
     * If the frame is available it switches the given driver to the specified frame.
     */
    fun frameAvailableAndSwitchToIt(element: WebElement) {
        wait().until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element))
    }

    /**
     * Checking whether the given frame is available by string [locator] to switch to.
     * If the frame is available it switches the given driver to the specified frame.
     */
    fun frameAvailableAndSwitchToIt(locator: String) {
        wait().until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator))
    }

    /**
     * Checking whether the given frame is available by [locator] to switch to.
     * If the frame is available it switches the given driver to the specified frame.
     */
    fun frameAvailableAndSwitchToIt(locator: By) {
        wait().until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator))
    }

    /**
     * Checking whether the given frame is available by [frameNumber] to switch to.
     * If the frame is available it switches the given driver to the specified frame.
     */
    fun frameAvailableAndSwitchToIt(frameNumber: Int) {
        wait().until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameNumber))
    }

}