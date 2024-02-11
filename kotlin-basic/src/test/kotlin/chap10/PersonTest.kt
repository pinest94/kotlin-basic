package chap10

import org.junit.jupiter.api.Test
import kotlin.reflect.full.functions
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.reflect

class PersonTest {

    /***
     * KClass
     */
    @Test
    fun personReflectionTestForKClass() {

        val person = Person(
            name = "Andrew",
            age = 30,
            company = CompanyImpl("LINE"),
        )

        val kClass = person.javaClass.kotlin
        println("kClass : $kClass")
        println("kClass.simpleName : ${kClass.simpleName}")
    }

    /***
     * KCallable
     */
    @Test
    fun personReflectionTestForKCallable() {

        val person = Person(
            name = "Andrew",
            age = 30,
            company = CompanyImpl("LINE"),
        )

        val kCallable = person::printAge

        println(kCallable.call())
    }
}