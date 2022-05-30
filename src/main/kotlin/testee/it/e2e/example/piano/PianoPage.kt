package testee.it.e2e.example.piano

import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.ExpectedConditions.numberOfElementsToBeMoreThan
import testee.it.e2e.example.BasePage

class PianoPage(driver: WebDriver) : BasePage(driver) {

    @FindBy(css = ".piano-menu__song-start")
    private lateinit var start: WebElement

    @FindBy(xpath = "//*[contains(text(), 'Accept all')]")
    private lateinit var acceptCookies: WebElement

    @FindBy(id = "key-highlight")
    private lateinit var menu: WebElement

    @FindBy(css = ".assist-menu")
    private lateinit var assistMenu: WebElement

    @FindBy(css = ".assist_highlight")
    private lateinit var highlight: WebElement

    override fun isOpened(): PianoPage = apply {
        clickable(acceptCookies)
    }

    private fun setAttribute(element: WebElement, attName: String, attValue: String) {
        val js = driver() as JavascriptExecutor
        js.executeScript(
            "arguments[0].setAttribute(arguments[1], arguments[2]);",
            element, attName, attValue
        )
    }

    fun acceptAllCookies(): PianoPage = apply {
        click(acceptCookies)
    }

    fun hideAdvertisement(): PianoPage = apply {
        driver().findElements(By.cssSelector(".ad-wrap")).forEach {
            setAttribute(it, "hidden", "true")
        }
    }


    fun setHighlight(): PianoPage = apply {
        click(menu)
        wait().until(ExpectedConditions.attributeContains(menu, "class", "opened"))
        wait().until(ExpectedConditions.attributeContains(assistMenu, "class", "active"))
        click(highlight)
        wait().until(ExpectedConditions.attributeContains(menu, "class", "active"))
        click(menu)
    }

    fun mentorPlay(pause: Long = 180): PianoPage = apply {
        setHighlight()
        waitForSeconds(5)
        click(start)
        println("Start")
        do {
            driver().findElements(By.cssSelector(".key-white.key-next, .key-black.key-next"))
                .forEach {
                    println(it.getAttribute("id") + ",")
                    click(it)
                }
            Thread.sleep(pause)
            println("|")
        } while (
            tick().until(numberOfElementsToBeMoreThan(By.cssSelector(".key-white.key-next, .key-black.key-next"), 0)).size > 0
        )
        println("Finish")
    }

    fun freePlay(note: Note, pause: Long = 180): PianoPage = apply {
        note.note.split("|").forEach {
            driver().findElements(By.cssSelector("#" + it.replace(",", ", #")))
                .forEach { key -> click(key) }
            Thread.sleep(pause)
        }
    }
}


