package testee.it.e2e.example.matryoshka

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.ui.ExpectedConditions.attributeContains
import org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable
import testee.it.e2e.core.pages.BasePage

class MatryoshkaPage(driver: WebDriver) : BasePage(driver) {

    companion object {
        private const val IMAGE_URL = "https://i.ibb.co/3p6XkbM/matryoshka.png"
    }

    @FindBy(id = "close-contest")
    private lateinit var popup: WebElement

    @FindBy(id = "home-open-url")
    private lateinit var createNewFromUrl: WebElement

    @FindBy(id = "image-url")
    private lateinit var imageUrl: WebElement

    @FindBy(css = "[id*='dialog-apply']")
    private lateinit var dialogApply: WebElement

    @FindBy(css = "[id*='dialog-cancel']")
    private lateinit var dialogCancel: WebElement

    //- - - - - - - -

    @FindBy(css = "[for='transform-toggle']")
    private lateinit var toolArrange: WebElement

    @FindBy(id = "arrange-top")
    private lateinit var toolArrangeTop: WebElement

    @FindBy(id = "arrange-left")
    private lateinit var toolArrangeLeft: WebElement

    @FindBy(id = "arrange-width")
    private lateinit var toolArrangeWidth: WebElement

    //- - - - - - - -

    @FindBy(id = "layer-box-add")
    private lateinit var addLayer: WebElement

    @FindBy(id = "layer-list")
    private lateinit var layerList: WebElement

    @FindBy(css = "#layer-list:first-child")
    private lateinit var layerListFirst: WebElement


    @FindBy(id = "add-layer-text")
    private lateinit var addLayerText: WebElement

    @FindBy(id = "tool-add-text")
    private lateinit var toolAddText: WebElement

    @FindBy(id = "text-input")
    private lateinit var textInput: WebElement

    @FindBy(id = "text-font-picker")
    private lateinit var textFontPicker: WebElement

    @FindBy(id = "font:Arial")
    private lateinit var fontArial: WebElement

    @FindBy(css = "#text-size .info input")
    private lateinit var textSize: WebElement

    //- - - - - - - -
    @FindBy(id = "save")
    private lateinit var save: WebElement

    @FindBy(id = "save-filename")
    private lateinit var saveFilename: WebElement

    @FindBy(id = "save-image-height")
    private lateinit var saveImageHeight: WebElement


    override fun navigate(url: String): MatryoshkaPage = apply {
        isLoaded().isOpened()
    }

    override fun isOpened(s: String): MatryoshkaPage = apply {
        wait.until(elementToBeClickable(createNewFromUrl))
    }

    override fun isLoaded(): MatryoshkaPage = apply {
        waitForLoaded()
    }

    fun closeAllModalDialogs(): MatryoshkaPage = apply {
        if (isVisible(popup)) {
            wait.until(elementToBeClickable(popup)).click()
        }
    }

    fun saveImage(value: Emoji) {
        wait.until(elementToBeClickable(layerListFirst)).click()
        sendTextViaJavascript(textInput, value.symbol)
        waitForLoaded()
        wait.until(elementToBeClickable(save)).click()
        wait.until(elementToBeClickable(saveFilename))
        sendTextViaJavascript(saveFilename, value.symbol)
        wait.until(elementToBeClickable(saveImageHeight))
        sendText(saveImageHeight, "512")
        wait.until(elementToBeClickable(dialogApply)).click()
        waitForSeconds(1)
        wait.until(attributeContains(dialogApply, "class", "positive"))
        wait.until(elementToBeClickable(dialogCancel)).click()
        wait.until(elementToBeClickable(toolAddText)).click()
    }

    fun pixlr(): MatryoshkaPage = apply {
        wait.until(elementToBeClickable(createNewFromUrl)).click()
        wait.until(elementToBeClickable(imageUrl))
        sendText(imageUrl, IMAGE_URL)
        wait.until(elementToBeClickable(dialogApply)).click()
        wait.until(elementToBeClickable(addLayer)).click()
        wait.until(elementToBeClickable(addLayerText)).click()
        sendTextViaJavascript(textInput, Emoji.values().random().symbol)

        wait.until(elementToBeClickable(toolArrange)).click()
        wait.until(elementToBeClickable(toolArrangeTop)).click()
        sendText(toolArrangeTop, "45")
        wait.until(elementToBeClickable(toolArrangeLeft)).click()
        sendText(toolArrangeLeft, "90")
        wait.until(elementToBeClickable(toolArrangeWidth)).click()
        sendText(toolArrangeWidth, "360")
        wait.until(elementToBeClickable(textSize)).click()
        sendText(textSize, "292")

        for (face in Emoji.values().copyOfRange(0, 10)) {
            wait.until(elementToBeClickable(layerListFirst)).click()
            sendTextViaJavascript(textInput, face.symbol)
        }

        println("Finished")
    }
}
