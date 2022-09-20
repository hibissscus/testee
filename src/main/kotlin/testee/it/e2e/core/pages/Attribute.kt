package testee.it.e2e.core.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions

interface Attribute : Driver {

    /**
     * Checking given [WebElement] has [attribute] with a specific [value]
     */
    fun attributeToBe(element: WebElement, attribute: String, value: String): Boolean {
        return wait().until(ExpectedConditions.attributeToBe(element, attribute, value))
    }

    /**
     * Checking [WebElement] has [attribute] which contains specific [value]
     */
    fun attributeContains(element: WebElement, attribute: String, value: String): Boolean {
        return wait().until(ExpectedConditions.attributeContains(element, attribute, value))
    }

    /**
     * Checking [WebElement] with given [locator] has [attribute] which contains specific [value]
     */
    fun attributeContains(locator: By, attribute: String, value: String): Boolean {
        return wait().until(ExpectedConditions.attributeContains(locator, attribute, value))
    }

    /**
     * Checking [WebElement] has any non-empty value for given [attribute]
     */
    fun attributeToBeNotEmpty(element: WebElement, attribute: String): Boolean {
        return wait().until(ExpectedConditions.attributeToBeNotEmpty(element, attribute))
    }

}