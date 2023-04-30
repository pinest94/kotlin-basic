package chap8

import org.junit.jupiter.api.Test
import java.lang.StringBuilder
import kotlin.math.sign

class HigherOrderFunctionTest {

    /***
     * 함수 타입을 선언을 통해 고차함수 구현을 배움.
     * Collection library에서 제공하는 여러가지 고차함수를 활용한 기능이 있다.
     * 예를 들어, filter, map...
     * 여기서는, 인자로 String만 받는 filter를 구현해보도록 하자.
     */

    @Test
    fun createFilter() {
        println("abcd123ef4g".filter { it in 'a' .. 'z' })
    }
}

fun String.filter(predicate: (Char) -> Boolean): String {
    val sb = StringBuilder()
    for(idx in 0 until length) {
        val element = get(idx)
        if(predicate(element)) sb.append(element)
    }
    return sb.toString()
}