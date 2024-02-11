package chap10

import chap10.annotation.JsonExclude
import chap10.annotation.JsonName
import collections.joinToString
import kotlin.reflect.KProperty1
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.memberProperties

class MyJackson {

    fun deserialize(personJsonData: String): Any {
        TODO("Not yet implemented")
    }

    fun serialize(obj: Any): String = buildString { serializeObject(obj) }

    /***
     * {
     *      "name": "andrew",
     *      "age": 30,
     *      "company": "line",
     *      "birthday": "2002-01-01"
     * }
     */
    private fun StringBuilder.serializeObject(obj: Any) {
        obj.javaClass.kotlin.memberProperties
            .filter { it.findAnnotation<JsonExclude>() == null }
            .joinToString(this, prefix = "{", postfix = "}") {
            serializeProperty(it, obj).toString()
        }
    }

    private fun StringBuilder.serializeProperty(
        props: KProperty1<Any, *>, obj: Any
    ) {
        serializeString(props)
        append(": ")
        serializePropertyValue()
    }

    private fun StringBuilder.serializePropertyValue() {
        TODO("Not yet implemented")
    }

    private fun serializeString(props: KProperty1<Any, *>): String {
        val jsonNameAnn = props.findAnnotation<JsonName>()
        return jsonNameAnn?.value ?: props.name
    }


}