package basic.iteration

class forDemo {
}

fun main() {
    val map = mutableMapOf<String, String>()
    map.put("hansol", "hansol@naver.com")
    map.put("jisoo", "jisoo@naver.com")
    map.put("sungjae", "sungjae@naver.com")
    map.put("jongyeon", "jongyeon@naver.com")

    for ((key, value) in map) {
        println("$key : $value")
    }
}