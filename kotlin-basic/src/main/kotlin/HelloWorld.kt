import shapes.Rectangle
import Color.*
import java.lang.Exception

fun max(a: Int, b: Int): Int = if (a > b) a else b

fun main(args: Array<String>) {
    val name = if (args.isNotEmpty()) args[0] else "Kotlin"
    println("hello, $name")
    println("hello, ${if (args.isNotEmpty()) args[0] else "someone"}")
    println(max(20, 399))

    val person = Person("hansol", false);
    println("person : " + person.name + ", " + person.isMarried)

    val rectangle = Rectangle(40, 40);
    println("rectangle is square? : " + rectangle.idSquare)

    println(GREEN)
    println(getMnemonic(VIOLET))
    println(mix(YELLOW, RED))
    println(refactoringEval(Sum(Sum(Num(1), Num(10)), Num(100))))
    println(isLetter('q'))
    println(recognize('z'))
}

fun getMnemonic(color: Color) =
    when (color) {
        RED -> "Richard"
        ORANGE -> "Of"
        YELLOW -> "York"
        GREEN -> "Gave"
        BLUE -> "Battle"
        INDIGO -> "In"
        VIOLET -> "Vain"
    }

fun mix(c1: Color, c2: Color) =
    when (setOf(c1, c2)) {
        setOf(RED, YELLOW) -> ORANGE
        setOf(YELLOW, BLUE) -> GREEN
        setOf(BLUE, VIOLET) -> INDIGO
        else -> throw Exception("Dirty Color")
    }