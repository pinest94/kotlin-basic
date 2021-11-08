package lambda

fun main() {
    println(listOf(1, 2, 3, 4)
        .asSequence()
        .map { println("map($it) "); it * it }
        .filter { println("filter($it)"); it % 2 == 0 }
        .find { it > 3 })

    val naturalNumbers = generateSequence(1) { it + 2 }
    val numbersTo100 = naturalNumbers.takeWhile { it <= 100 }
    println(numbersTo100.sum())
}