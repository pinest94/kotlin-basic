package chap10

import chap10.annotation.JsonExclude
import chap10.annotation.JsonName

data class Person(
    @JsonName("firstName")
    val name: String,
    val age: Int,
    @JsonExclude
    val address: String = "Seoul",
)
