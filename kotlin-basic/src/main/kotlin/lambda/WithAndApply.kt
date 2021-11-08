package lambda

fun main() {
    val coffee = Coffee()
    println(coffee.alphabet())
}

class Coffee {
    override fun toString() = "Hansol"

    fun alphabet() = StringBuilder().apply {
        for (letter in 'A' .. 'Z') {
            append(letter)
        }
        append("\nNow I know the alphabet!")
    }.toString()
}

