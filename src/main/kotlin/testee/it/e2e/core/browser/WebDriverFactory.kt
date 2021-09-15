package testee.it.e2e.core.browser

import com.google.common.base.Strings
import org.openqa.selenium.WebDriverException
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions
import org.openqa.selenium.firefox.FirefoxProfile
import org.openqa.selenium.remote.RemoteWebDriver
import java.net.URL
import java.util.concurrent.TimeUnit


/**
 * Base class for web tests which contains [RemoteWebDriver] web driver
 */
object WebDriverFactory {

    /**
     * Initialize driver and starts browser [Browser]
     * @param browser - one of [Browser]
     * @param headless - use or not headless mode
     * @param mobile - use or not mobile mode
     * @param media - use or not media
     * @param selenium - selenium hub address
     */
    @Throws(Exception::class)
    fun startBrowser(
        browser: Browser = Browser.CHROME, headless: Boolean = false,
        mobile: Boolean = false, media: Boolean = true,
        fake: String = "", selenium: String = ""
    ): RemoteWebDriver {
        return when (browser) {
            Browser.FIREFOX -> initFirefoxWebDriver(headless, media, fake, selenium)
            Browser.CHROME -> initChromeWebDriver(headless, mobile, media, fake, selenium)
        }
    }

    /**
     * Setup timeouts, navigate to initial page, put focus on window
     * @param driver for browser [Browser]
     * @param url for browser [Browser]
     */
    fun manageBrowser(driver: RemoteWebDriver, url: String, defaultWait: Long): RemoteWebDriver = driver.apply {
        driver.manage().timeouts().implicitlyWait(defaultWait, TimeUnit.SECONDS)
        driver.manage().timeouts().pageLoadTimeout(defaultWait, TimeUnit.SECONDS)
        driver.manage().timeouts().setScriptTimeout(defaultWait, TimeUnit.SECONDS)
        driver.manage().deleteAllCookies()

        // info about browser versions
        getInfo(driver)

        // navigate to entry point page
        if (url.isNotBlank()) {
            try {
                driver.navigate().to(url)
            } catch (ex: WebDriverException) {
            }
        }

        // put focus on window
        val currentWindow = driver.windowHandle
        driver.switchTo().window(currentWindow)
    }

    /**
     * Get information about current [RemoteWebDriver]
     */
    fun getInfo(driver: RemoteWebDriver) {
        return when (driver) {
            is ChromeDriver -> {
                println("Chrome: " + driver.capabilities.version)
                println("ChromeDriver: " + (driver.capabilities.getCapability("chrome") as Map<*, *>)["chromedriverVersion"])
            }
            is FirefoxDriver -> {
                println("Firefox: " + driver.capabilities.version)
            }
            else -> {
                println("TODO")
            }
        }
    }

    /**
     * Initialize driver and starts browser for [Browser.FIREFOX]
     * @param headless - use or not headless mode
     * @param media - use or not media
     * @param selenium - selenium hub address
     */
    @Throws(Exception::class)
    private fun initFirefoxWebDriver(headless: Boolean, media: Boolean, fake: String, selenium: String): RemoteWebDriver {
        val opt = FirefoxOptions()

        val profile = FirefoxProfile()
        profile.setPreference("browser.privatebrowsing.autostart", true)
        profile.setPreference("general.useragent.locale", "en")
        profile.setPreference("intl.accept_languages", "en")
        profile.setPreference("browser.download.useDownloadDir", false)
        opt.addPreference("permissions.default.microphone", if (media) 1 else 2)
        opt.addPreference("permissions.default.camera", if (media) 1 else 2)
        opt.addArguments("--window-size=1300,900")

        if (headless) {
            opt.setHeadless(headless)
            profile.setPreference("media.navigator.streams.fake", true)
            // disable unresponsive script alerts
            profile.setPreference("dom.max_script_run_time", 0)
            profile.setPreference("dom.max_chrome_script_run_time", 0)
            // don't skip proxy for localhost
            profile.setPreference("network.proxy.no_proxies_on", "")
            // prevent different kinds of popups/alerts
            profile.setPreference("browser.tabs.warnOnClose", false)
            profile.setPreference("browser.tabs.warnOnOpen", false)
            profile.setPreference("extensions.newAddons", false)
            profile.setPreference("extensions.update.notifyUser", false)
        }
        profile.setPreference("media.navigator.streams.fake", fake.isNotEmpty())
        opt.profile = profile

        return if (!Strings.isNullOrEmpty(selenium) && headless) {
            RemoteWebDriver(URL(selenium), opt)
        } else {
            FirefoxDriver(opt)
        }
    }

    /**
     * Initialize driver and starts browser for [Browser.CHROME]
     * @param headless - use or not headless mode
     * @param mobile - use or not mobile mode
     * @param media - use or not media
     * @param selenium - selenium hub address
     */
    private fun initChromeWebDriver(headless: Boolean, mobile: Boolean, media: Boolean, fake: String, selenium: String): RemoteWebDriver {
        val opt = ChromeOptions()

        if (mobile) {
            val deviceMetrics = HashMap<String, Any>()
            deviceMetrics["width"] = 800
            deviceMetrics["height"] = 600
            deviceMetrics["pixelRatio"] = 3.0

            val mobileEmulation = HashMap<String, Any>()
            mobileEmulation["deviceMetrics"] = deviceMetrics
            mobileEmulation["userAgent"] = "Mozilla/5.0 (iPad; CPU OS 11_0 like Mac OS X) " +
                    "AppleWebKit/604.1.34 (KHTML, like Gecko) Version/11.0 Mobile/15A5341f Safari/604.1"

            opt.setExperimentalOption("mobileEmulation", mobileEmulation)
        }

        if (headless) {
            opt.setHeadless(headless)
            opt.addArguments("--disable-dev-shm-usage")
        }

        if (fake.isNotEmpty()) {
            opt.addArguments("--disable-user-media-security")
            opt.addArguments("--use-fake-ui-for-media-stream")
            opt.addArguments("--use-fake-device-for-media-stream")
            if (!headless) {
                opt.addArguments("--use-file-for-fake-video-capture=${System.getProperty("user.dir")}/src/test/resources/fake/fakeA.mjpeg")
            }
        }

        opt.setAcceptInsecureCerts(true)
        //opt.addArguments("--incognito")
        opt.addArguments("--disable-notifications")
        opt.addArguments("--window-size=1280,800")
        opt.addArguments("--lang=de")


        val prefs = HashMap<String, Any>()
        // pass the argument 1 to allow and 2 to block
        prefs["profile.default_content_setting_values.media_stream_mic"] = if (media) 1 else 2
        prefs["profile.default_content_setting_values.media_stream_camera"] = if (media) 1 else 2

        return if (!Strings.isNullOrEmpty(selenium) && headless) {
            prefs["download.default_directory"] = "/home/seluser/"
            opt.setExperimentalOption("prefs", prefs)
            RemoteWebDriver(URL(selenium), opt)
        } else {
            prefs["download.default_directory"] = System.getProperty("user.dir") + "/downloads"
            opt.setExperimentalOption("prefs", prefs)
            ChromeDriver(opt)
        }
    }
}
