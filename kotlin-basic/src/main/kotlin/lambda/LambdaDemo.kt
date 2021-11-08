package lambda

fun main() {
    val people = listOf(
        Person("Alice", 28),
        Person("Bob", 31),
        Person("Brown", 32),
        Person("Kotlin", 28)
    )
    println(people.filter { it.age > 30 })
    println(people)
    println(people.map { it.age > 30 })
    println(people)

    println(people.all { it.age > 30 })
    println(people.any { it.age > 30 })
    println(people.find { it.age > 28 })
    println(people.findLast { it.age > 28 })
    println(people.count { it.age > 30 })
    var map = people.groupBy { it.age }
    println(map[28]!![0].name)
    println(map[28]!![1].name)

    val corpList = listOf(
        Corp("Samsung", listOf("삼성전자", "삼성SDS", "삼성SDI", "삼성물산", "삼성화재")),
        Corp("LINE", listOf("라인플러스", "라인비즈플러스", "라인파이낸셜플러스"))
    )

    println(corpList.flatMap { it.sonCorpList })
}
