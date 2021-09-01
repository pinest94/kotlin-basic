package basic


fun maxOfTwoNumbers(a: Int, b: Int): Int = if (a > b) a else b

fun main() {
    println(maxOfTwoNumbers(10, 20))
    println(printName("hansol"))
}

fun printName(name: String) = "Hello, $name"