package testee.it.e2e.example.slack.api

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import testee.it.e2e.example.BasePage

class SlackApiPage(driver: WebDriver) : BasePage(driver) {

    @FindBy(id = "onetrust-accept-btn-handler")
    private lateinit var acceptCookies: WebElement

    @FindBy(id = "api-arg-token")
    private lateinit var token: WebElement

    @FindBy(xpath = "//label[contains(text(), 'channel')]/following-sibling::input")
    private lateinit var channel: WebElement

    @FindBy(xpath = "//label[contains(text(), 'text')]/following-sibling::input")
    private lateinit var text: WebElement

    @FindBy(css = "[type='submit'][value='Test method']")
    private lateinit var submit: WebElement

    override fun isOpened(): SlackApiPage = apply {
        clickable(acceptCookies)
    }

    fun acceptAllCookies(): SlackApiPage = apply {
        if (isVisible(acceptCookies)) {
            click(acceptCookies)
        }
    }

    fun setToken(tokenStr: String): SlackApiPage = apply {
        sendText(token, tokenStr)
    }

    fun setChannel(channelStr: String): SlackApiPage = apply {
        sendText(channel, channelStr)
    }

    fun setText(textStr: String): SlackApiPage = apply {
        sendText(text, textStr)
    }

    fun submit(): SlackApiPage = apply {
        click(submit)
    }
}
