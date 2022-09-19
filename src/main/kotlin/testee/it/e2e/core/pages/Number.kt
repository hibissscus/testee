package testee.it.e2e.core.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions

interface Number : Driver {

    /**
     *  Checking [number] of [WebElement]s with given [locator]
     */
    fun numberOfElementsToBe(locator: By, number: Int): List<WebElement> {
        return wait().until(ExpectedConditions.numberOfElementsToBe(locator, number))
    }

    /**
     *  Checking [number] of [WebElement]s with given [locator] to be more than
     */
    fun numberOfElementsToBeMoreThan(locator: By, number: Int): List<WebElement> {
        return wait().until(ExpectedConditions.numberOfElementsToBeMoreThan(locator, number))
    }

    /**
     *   Checking [number] of [WebElement]s with given [locator] to be less than
     */
    fun numberOfElementsToBeLessThan(locator: By, number: Int): List<WebElement> {
        return wait().until(ExpectedConditions.numberOfElementsToBeLessThan(locator, number))
    }

}