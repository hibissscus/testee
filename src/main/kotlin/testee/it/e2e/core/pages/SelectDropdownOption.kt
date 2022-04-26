package testee.it.e2e.core.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.Select

interface SelectDropdownOption : Driver {

    /**
     * Select in dropdown list [Select]
     */
    fun selectDropdownOption(by: By, value: String) {
        val selectList = Select(driver().findElements(by).first())
        selectList.selectByValue(value)
    }

    /**
     * Select in dropdown list by value [Select]
     */
    fun selectDropdownOption(element: WebElement, value: String) {
        val selectList = Select(element)
        selectList.selectByValue(value)
    }
}