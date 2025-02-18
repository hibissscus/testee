package testee.it.e2e.core.pages

interface Screenshot : Driver {

    fun screenshot(): (String) -> Unit
}