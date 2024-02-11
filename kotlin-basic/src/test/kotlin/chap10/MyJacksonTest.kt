package chap10

import collections.joinToString
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.reflect.full.memberProperties
import java.lang.StringBuilder

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
        val person = Person(
            name = "Andrew",
            age = 30,
            company = CompanyImpl("LINE"),
        )

        val kClass = person.javaClass.kotlin
        val properties = kClass.memberProperties
        val builder = StringBuilder()

        val json = properties.joinToString(builder, prefix = "{", postfix = "}") {
            builder.append(it.name)
            builder.append(": ")
            builder.append(it.get(person).toString())
        }
        println(json)
    }
}