package chap10

import chap10.annotation.CustomSerializer
import chap10.annotation.DeserializeInterface
import chap10.annotation.JsonExclude
import chap10.annotation.JsonName
import chap10.serialize.DateSerialize
import java.util.Date

data class Person(
    @JsonName("firstName")
    val name: String,
    val age: Int,
    @JsonExclude
    val address: String = "Seoul",
    @DeserializeInterface(CompanyImpl::class)
    val company: Company,
    @CustomSerializer(DateSerialize::class)
    val birthday: Date? = null
) {
    fun printAge() = "당신의 나이는 ${age}세 입니다."
}
