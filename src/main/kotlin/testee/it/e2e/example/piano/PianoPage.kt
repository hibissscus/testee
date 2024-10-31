package testee.it.e2e.example.piano

import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.ui.ExpectedConditions
import testee.it.e2e.example.BasePage

class PianoPage(driver: WebDriver) : BasePage(driver) {

    @FindBy(css = ".piano-menu__song-start")
    private lateinit var start: WebElement

    @FindBy(id = "key-highlight")
    private lateinit var menu: WebElement

    @FindBy(css = ".assist-menu")
    private lateinit var assistMenu: WebElement

    @FindBy(css = ".assist_highlight")
    private lateinit var highlight: WebElement

    override fun opened(): PianoPage = apply {
    }

    private fun setAttribute(element: WebElement, attName: String, attValue: String) {
        val js = driver() as JavascriptExecutor
        js.executeScript(
            "arguments[0].setAttribute(arguments[1], arguments[2]);",
            element, attName, attValue
        )
    }

    fun hideCookies(): PianoPage = apply {
        driver().findElements(By.id("qc-cmp2-container")).forEach {
            setAttribute(it, "hidden", "true")
        }
    }

    fun hideAdvertisement(): PianoPage = apply {
        driver().findElements(By.cssSelector(".ad-wrap")).forEach {
            setAttribute(it, "hidden", "true")
        }
    }


    fun setHighlight(): PianoPage = apply {
        click(menu)
        attributeContains(menu, "class", "opened")
        attributeContains(assistMenu, "class", "active")
        click(highlight)
        attributeContains(menu, "class", "active")
        click(menu)
    }

    fun mentorPlay(pause: Long = 180): PianoPage = apply {
//        setHighlight()
//        waitForSeconds(5)
//        click(start)
        println("Start")
        do {
            val start = driver().findElement(By.id("song-pattern")).findElements(By.tagName("span"))
                .first().text
            Actions(driver).sendKeys("start").perform()
            driver().findElement(By.id("song-pattern")).findElements(By.tagName("span"))
                .filter { !it.text.equals(".") }
                .drop(0)
                .forEach {
                    println(it.text + ",")
                    click(it)
                    Thread.sleep(pause)
                }

            println("|")
        } while (
            tick().until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector(".key-white.key-next, .key-black.key-next"), 0)).size > 0
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


