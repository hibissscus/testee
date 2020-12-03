package testee.it.e2e.core.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.Select

interface SelectDropdownOption {

    /**
     * Select in dropdown list [Select]
     */
    fun selectDropdownOption(driver: WebDriver, by: By, value: String): SelectDropdownOption = apply {
        val selectList = Select(driver.findElements(by).first())
        selectList.selectByValue(value)
    }

    /**
     * Select in dropdown list by value [Select]
     */
    fun selectDropdownOption(element: WebElement, value: String): SelectDropdownOption = apply {
        val selectList = Select(element)
        selectList.selectByValue(value)
    }
}