package collections

import java.io.BufferedReader

fun main() {
    val list = listOf("hansol", "sungjae", "jongyeon")
    println(joinToString(collection = list))

//    val reader = BufferedReader(InputStreamReader(System.`in`))
//    val numbers = readNumbers(reader)
//    addValidNumbers(numbers)
}

fun readNumbers(reader: BufferedReader): List<Int?> {
    val result = ArrayList<Int?>()
    for (line in reader.lineSequence()) {
        if (line == "exit") {
            return result
        }
        result.add(line.toIntOrNull())
    }

    return result
}

fun addValidNumbers(numbers: List<Int?>) {
    var sumOfValidNumbers = numbers.filterNotNull()

    println("valid : ${sumOfValidNumbers.sum()}")
    println("invalid : ${numbers.size - sumOfValidNumbers.size}")
}

fun <T> joinToString(
    collection: Collection<T>,
    separator: String = ", ",
    prefix: String = "",
    postfix: String = ""
): String {
    val result = StringBuilder(prefix)

    for((index, element) in collection.withIndex()) {
        if(index > 0) result.append(separator)
        result.append(element)
    }

    result.append(postfix)
    return result.toString()
}