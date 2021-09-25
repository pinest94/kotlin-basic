package lambda

fun main() {

    var people: MutableList<Person> = mutableListOf()

    for (i in 1..100) {
        people.add(Person("aaa", i % 3))
    }

    var startTime = System.currentTimeMillis()

    println(people
        .asSequence()
        .filter { it.age % 2 == 1 }
        .map { it.name }
        .count())

    var endTime = System.currentTimeMillis()
    println("elapsedTime : ${endTime - startTime}")
}

