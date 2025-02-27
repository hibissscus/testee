package testee.it.e2e.core.test

import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot
import org.openqa.selenium.WebDriver
import org.openqa.selenium.remote.RemoteWebDriver
import org.testng.ITestContext
import org.testng.ITestResult
import org.testng.SkipException
import org.testng.annotations.AfterClass
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeClass
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Listeners
import org.testng.annotations.Optional
import org.testng.annotations.Parameters
import org.testng.util.Strings
import testee.it.e2e.core.browser.Browser
import testee.it.e2e.core.browser.WebDriverFactory.manageBrowser
import testee.it.e2e.core.browser.WebDriverFactory.startBrowser
import testee.it.reportng.HTMLReporter
import java.io.File
import java.lang.System.currentTimeMillis
import java.lang.reflect.Method
import java.util.*
import kotlin.concurrent.timerTask


/**
 * This class is an entry point for all e2e tests in test suite.
 *
 * All test method names should start with something like: `01 test`, `02 test`, `03 test`,
 * Any instance of [TestBase] will execute test methods by the method name, in lexicographic order.
 */
@Listeners(HTMLReporter::class)
abstract class TestBase(
    protected var url: String = "",
    protected var browser: Browser = Browser.CHROME,
    protected var headless: Boolean = false,
    protected var mobile: Boolean = false,
    protected var media: Boolean = true,
    protected var fake: String = "",
    protected var selenium: String = ""
) {

    /**
     * This is an instance of [WebDriver] which will be used along the test.
     * It will be lazily initialized  inside driverSetup method [TestBase.driverSetup].
     */
    lateinit var driver: RemoteWebDriver protected set
    protected var hasFailures = 0
    lateinit var timer: Timer
    var counter = currentTimeMillis()

    /**
     * This part will be executed before any other test methods.
     */
    @BeforeClass
    @Parameters("url", "selenium", "browser", "headless", "mobile")
    fun driverSetup(
        @Optional("") optUrl: String,
        @Optional("") optSelenium: String,
        @Optional("") optBrowser: String,
        @Optional("") optHeadless: String,
        @Optional("") optMobile: String,
    ) {

        if (!Strings.isNullOrEmpty(optUrl) && url.isBlank()) url = optUrl.replace("_", "-")
        if (!Strings.isNullOrEmpty(optSelenium)) selenium = optSelenium.replace("_", "-")
        if (!Strings.isNullOrEmpty(optBrowser)) browser = Browser.valueOf(optBrowser)
        if (!Strings.isNullOrEmpty(optHeadless)) headless = optHeadless.toBoolean()
        if (!Strings.isNullOrEmpty(optMobile)) mobile = optMobile.toBoolean()

        driverStart()

        timer = Timer()
        timer.scheduleAtFixedRate(
            timerTask {
                takeScreenShot()
            }, 500, 500
        )
    }

    /**
     * Default wait fo driver configuration
     */
    protected open fun defaultWait(): Long {
        return 25L
    }

    /**
     * Default wait fo driver configuration
     */
    protected open fun maxRetries(): Int {
        return RetryAnalyzer.maxRetries
    }

    /**
     * Start new instance of the [WebDriver]
     */
    protected open fun driverStart() {
        driver = manageBrowser(startBrowser(browser, headless, mobile, media, fake, selenium), url, defaultWait())
    }

    /**
     * Finalization part will be executed after all other test methods.
     */
    @AfterClass(alwaysRun = true)
    fun driverQuit() {
        if (this::driver.isInitialized) driver.quit()
//        timer.cancel()
    }

    /**
     * ... REPORTNG PART ...
     */
    protected lateinit var outputDirectory: String
    protected lateinit var className: String
    protected lateinit var testName: String
    protected lateinit var itc: ITestContext

    @BeforeClass
    fun beforeClass(context: ITestContext) {
        itc = context
        outputDirectory = context.outputDirectory.replace("Default Suite", "e2e")
        className = this.javaClass.name
        itc.allTestMethods.first().retryAnalyzerClass = RetryAnalyzer::class.java
    }

    @BeforeMethod
    fun beforeTest(method: Method) {
        testName = method.name

        synchronized(this) {
            // skip all other test methods if we have errors
            if (hasFailures >= maxRetries()) {
                throw SkipException("Skipping this test")
            }
        }
    }

    @AfterMethod
    fun afterTest(method: Method) {
        testName = method.name

        // take a screenshot if this test method failed
        if (itc.failedTests.allMethods.stream().anyMatch { t -> t.methodName == testName }) {
            synchronized(this) {
                hasFailures++
                takeScreenShot()
            }
        }

        // in case if we allow retry for the first method in a test class
        if (itc.allTestMethods.first().methodName == testName) {
            val results = getCorrespondingResultFor(itc, method)
            if (results != null && (itc.allTestMethods.first()
                    .getRetryAnalyzer(results) as RetryAnalyzer).retries == maxRetries()
            ) {
                // clean up test results from duplication if we do retry
                itc.skippedTests.allResults.forEach { i ->
                    if (itc.skippedTests.allMethods.contains(i.method)) {
                        itc.skippedTests.allMethods.remove(i.method)
                    }
                }
                driverQuit()
                driverStart()
            }
        }
    }

    fun takeScreenShot(name: String = "") {
        val screenshotFile = (driver as TakesScreenshot).getScreenshotAs(OutputType.FILE)
        val outputFolder = "${outputDirectory}/images/$className/$testName/$name" + "_" + this.counter++ + ".png"
        screenshotFile.copyTo(File(outputFolder))
    }

    fun getCorrespondingResultFor(context: ITestContext, method: Method): ITestResult? {
        val allResults: MutableSet<ITestResult?> = HashSet()
        allResults.addAll(context.passedTests.allResults)
        allResults.addAll(context.failedTests.allResults)
        allResults.addAll(context.skippedTests.allResults)
        return allResults
            .stream()
            .filter { result: ITestResult? -> result!!.method.constructorOrMethod.method == method }
            .findAny()
            .orElse(null)
    }
}