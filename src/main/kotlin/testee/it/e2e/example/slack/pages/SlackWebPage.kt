package testee.it.e2e.example.slack.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import testee.it.e2e.example.BasePage

class SlackWebPage(driver: WebDriver, screenshot: (String) -> Unit = {}) : BasePage(driver, screenshot) {

    @FindBy(css = "[data-qa='channel_sidebar_name_test']")
    private lateinit var testChannel: WebElement

    override fun opened(): SlackWebPage = apply {
        clickable(testChannel)
    }

    fun findMessage(text: String): SlackWebPage = apply {
        clickable(By.xpath("//*[contains(text(),'$text')]"))
    }
}