package collections

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.NumberFormatException

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val numbers = readNumbers(reader)
    addValidNumbers(numbers)
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