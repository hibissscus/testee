package testee.it.e2e.example.matryoshka

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import testee.it.e2e.example.BasePage

class MatryoshkaPage(driver: WebDriver, screenshot: (String) -> Unit = {}) : BasePage(driver, screenshot) {

    @FindBy(id = "close-contest")
    private lateinit var popup: WebElement

    @FindBy(css = ".accept")
    private lateinit var acceptCookieAgreement: WebElement

    @FindBy(id = "splash-file-menu")
    private lateinit var menu: WebElement

    @FindBy(id = "splash-file-url")
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

    override fun opened(): MatryoshkaPage = apply {
        clickable(createNewFromUrl)
    }

    fun closeAllModalDialogs(): MatryoshkaPage = apply {
        clickIfVisible(popup)
    }

    fun acceptCookieAgreement(): MatryoshkaPage = apply {
        clickIfVisible(acceptCookieAgreement)
    }

    fun acceptPrivacyAgreement(): MatryoshkaPage = apply {
        click(By.xpath("//button[contains(text(), 'Agree and proceed')]"))
    }

    fun openNewImageFromUrl(imageUrlStr: String): MatryoshkaPage = apply {
        clickIfVisible(menu)
        visible(createNewFromUrl)
        click(createNewFromUrl)
        sendText(imageUrl, imageUrlStr)
        click(dialogApply)
    }

    fun saveImage(value: Emoji) {
        click(layerListFirst)
        sendTextViaJavascript(textInput, value.symbol)
        waitForLoaded()
        click(save)
        click(By.xpath("//a[contains(text(), 'Save as')]"))
        // check dialog
        clickable(saveFilename)
        sendTextViaJavascript(saveFilename, value.symbol)
        clickable(saveImageHeight)
        sendText(saveImageHeight, "512")
        click(dialogApply)
        attributeContains(dialogApply, "class", "positive")
        click(dialogCancel)
        click(toolAddText)
    }

    fun pixlrPreset(): MatryoshkaPage = apply {
        click(addLayer)
        click(addLayerText)
        sendTextViaJavascript(textInput, Emoji.values().random().symbol)
        click(toolArrange)
        acceptPrivacyAgreement()
        click(toolArrangeTop)
        sendText(toolArrangeTop, "45")
        click(toolArrangeLeft)
        sendText(toolArrangeLeft, "90")
        click(toolArrangeWidth)
        sendText(toolArrangeWidth, "360")
        click(textSize)
        sendText(textSize, "292")
    }


    fun emoji(waitForSeconds: Int = 0): MatryoshkaPage = apply {
        for (face in Emoji.values().copyOfRange(0, 10)) {
            click(layerListFirst)
            sendTextViaJavascript(textInput, face.symbol)
            waitForSeconds(waitForSeconds)
        }
        println("Finished")
    }
}
