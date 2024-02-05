package chap10

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MyJacksonTest {

    private lateinit var myJackson: MyJackson

    @BeforeEach
    fun setUp() {
        myJackson = MyJackson()
    }

    @Test
    fun deserializeTest() {
        val personJsonData = """
            {
                "name": "Andrew",
                "age": 30
            }
        """.trimIndent()
        println(myJackson.deserialize(personJsonData))
    }

    @Test
    fun serializeTest() {
        val person = Person(name = "Andrew", age = 30)

        println(myJackson.serialize(person))
    }
}