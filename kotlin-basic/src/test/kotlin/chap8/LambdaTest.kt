package chap8

import org.junit.jupiter.api.Test

class LambdaTest {

    @Test
    fun lambdaTest() {
        println("hello before")
        hello { println("hello") }
        println("hello after")
        hello2 { println("hello2") }
    }

    private inline fun hello(lambda: () -> Unit) {
        println("hello")
        lambda()
    }

    private fun hello2(lambda: () -> Unit) {
        println("hello2")
        lambda()
    }

    /***
     * inline
     */
    @Test
    fun forEachTest() {
        val list = mutableListOf<Long>()

        for (i in 1L .. 1_000_000_0L) { list.add(i) }

        var sum1 = 0L
        var sum2 = 0L

        val startTime1 = System.currentTimeMillis()
        list.forEachNotInline {
            if(it % 2L == 0L) { sum1 += it }
            else { sum1 -= it}
        }
        val endTime1 = System.currentTimeMillis()

        println("The sum is $sum1")
        println("elapsedTime : ${endTime1 - startTime1}")

        val startTime2 = System.currentTimeMillis()
        list.forEach {
            if(it % 2L == 0L) { sum2 += it }
            else { sum2 -= it}
        }
        val endTime2 = System.currentTimeMillis()

        println("The sum is $sum2")
        println("elapsedTime : ${endTime2 - startTime2}")
    }

    private fun <T> Iterable<T>.forEachNotInline(action: (T) -> Unit) {
        for (element in this) action(element)
    }
}