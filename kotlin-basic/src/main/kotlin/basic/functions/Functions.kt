package basic.functions


fun maxOfTwoNumbers(a: Int, b: Int): Int = if (a > b) a else b

fun main() {
    val view: View = Button()
    view.click()
    view.showoff()
}

fun printName(name: String) = "Hello, $name"

fun String.lastChar(): Char = get(length-1)

open class View {
    open fun click() = println("View Clicked")
}

open class Button: View() {
    override fun click() = println("Button Clicked")
}

fun View.showoff() = println("*** view ***")
fun Button.showoff() = println("*** button ***")