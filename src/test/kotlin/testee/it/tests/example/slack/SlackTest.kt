package testee.it.tests.example.slack

import org.testng.SkipException
import org.testng.annotations.Test
import testee.it.e2e.core.browser.Browser
import testee.it.e2e.core.test.TestBase
import testee.it.e2e.example.BasePage.Companion.loaded
import testee.it.e2e.example.BasePage.Companion.newTab
import testee.it.e2e.example.BasePage.Companion.switchTab
import testee.it.e2e.example.BasePage.Companion.view
import testee.it.e2e.example.BasePage.Companion.waitForSeconds
import testee.it.e2e.example.slack.model.Channels
import testee.it.e2e.example.slack.model.Slack
import testee.it.e2e.example.slack.model.Tokens
import testee.it.e2e.example.slack.model.User
import testee.it.e2e.example.slack.model.Workspace
import testee.it.e2e.example.slack.pages.SlackApiPage
import testee.it.e2e.example.slack.pages.SlackWebLoginPage
import testee.it.e2e.example.slack.pages.SlackWebPage
import kotlin.random.Random


class SlackTest : TestBase(url = Slack.WEB.slack, browser = Browser.CHROME) {

    data class State(var text: String = "text_" + Random.nextInt(10000))

    private val postMessage = State()

    @Test
    fun `01 login into slack web`() {
        SlackWebLoginPage(driver)
            .opened()
            .acceptAllCookies()
            .login(Workspace.WORKSPACE, User.TEST)
            .view(SlackWebPage(driver))
    }

    @Test
    fun `02 postMessage via slack api`() {
        SlackApiPage(driver)
            .newTab("https://api.slack.com/methods/chat.postMessage/test")
            .setToken(Tokens.TEST.token)
            .setChannel(Channels.TEST.channel)
            .setText(postMessage.text)
            .waitForSeconds(2)
            .submit()
    }

    @Test
    fun `03 check message into slack web`() {
        SlackWebPage(driver)
            .switchTab(0)
            .findMessage(postMessage.text)
            .waitForSeconds(2)
    }

    @Test
    fun `04 some exception`() {
        assert(false) { "this test failed" }
    }

    @Test
    fun `05 skipped test`() {
        throw SkipException("this will be skipped")
    }
}