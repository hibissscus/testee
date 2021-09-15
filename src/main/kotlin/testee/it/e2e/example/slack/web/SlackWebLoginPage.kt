package testee.it.e2e.example.slack.web

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import testee.it.e2e.example.BasePage
import testee.it.e2e.example.slack.model.User

class SlackWebLoginPage(driver: WebDriver) : BasePage(driver) {

    @FindBy(css = "[data-qa='signin_domain_input']")
    private lateinit var login: WebElement

    @FindBy(css = "[data-qa='submit_team_domain_button']")
    private lateinit var loginSubmit: WebElement

    @FindBy(css = "[data-qa='login_email']")
    private lateinit var loginEmail: WebElement

    @FindBy(css = "[data-qa='login_password']")
    private lateinit var loginPassword: WebElement

    @FindBy(css = "[data-qa='signin_button']")
    private lateinit var signButton: WebElement

    @FindBy(css = "[data-qa='continue_in_browser']")
    private lateinit var continueInBrowser: WebElement

    override fun isOpened(): SlackWebLoginPage = apply {
        clickable(login)
        clickable(loginSubmit)
    }

    private fun continueInBrowser(): SlackWebLoginPage = apply {
        if (isVisible(continueInBrowser)) {
            click(continueInBrowser)
        }
    }

    fun login(user: User): SlackWebLoginPage = apply {
        sendText(login, "cubicam")
        click(loginSubmit)
        sendText(loginEmail, user.username)
        sendText(loginPassword, user.password)
        click(signButton)
        continueInBrowser()
    }

}
