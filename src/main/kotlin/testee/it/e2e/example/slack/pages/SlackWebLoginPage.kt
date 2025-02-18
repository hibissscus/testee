package testee.it.e2e.example.slack.pages

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import testee.it.e2e.example.BasePage
import testee.it.e2e.example.slack.model.User
import testee.it.e2e.example.slack.model.Workspace

class SlackWebLoginPage(driver: WebDriver, screenshot: (String) -> Unit) : BasePage(driver, screenshot) {

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

    @FindBy(css = "[id='onetrust-accept-btn-handler']")
    private lateinit var acceptAllCookies: WebElement

    override fun opened(): SlackWebLoginPage = apply {
        clickable(login)
        clickable(loginSubmit)
    }

    private fun continueInBrowser(): SlackWebLoginPage = apply {
        clickIfVisible(continueInBrowser)
    }

    fun acceptAllCookies(): SlackWebLoginPage = apply {
        click(acceptAllCookies)
    }

    fun login(workspace: Workspace, user: User): SlackWebLoginPage = apply {
        sendText(login, workspace.workspace)
        click(loginSubmit)
        sendText(loginEmail, user.username)
        sendText(loginPassword, user.password)
        click(signButton)
        continueInBrowser()
    }

}
