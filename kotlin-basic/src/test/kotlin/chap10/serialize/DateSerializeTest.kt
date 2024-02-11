package chap10.serialize

import chap10.CompanyImpl
import chap10.Person
import org.junit.jupiter.api.Test
import java.text.SimpleDateFormat

class DateSerializerTest {
    @Test
    fun test() {
        val value = Person(
            name = "Alice",
            age = 30,
            address = "sungnam",
            birthday = SimpleDateFormat("dd-mm-yyyy").parse("13-02-1987"),
            company = CompanyImpl("LINE")
        )
        val json = """{"birthDate": "13-02-1987", "name": "Alice", "age": 30, "company": "LINE"}"""
    }
}