package `class`

fun main() {
    val person = Person("hansol", false)
    println("${person.name}, ${person.isMarried}")
    person.isMarried = true
    println("${person.name}, ${person.isMarried}")

    val validation: AccountValidation
}

class Person(val name: String, var isMarried: Boolean)