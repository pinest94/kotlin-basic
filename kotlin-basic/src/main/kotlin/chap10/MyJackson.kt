package chap10

import chap10.annotation.CustomSerializer
import chap10.annotation.JsonExclude
import chap10.annotation.JsonName
import chap10.serialize.ValueSerializer
import collections.joinToString
import kotlin.reflect.KProperty
import kotlin.reflect.KProperty1
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.memberProperties

class MyJackson {

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
        prop: KProperty1<Any, *>, obj: Any
    ) {
        val name = prop.findAnnotation<JsonName>()?.value ?: prop.name
        serializeString(name)
        append(": ")

        val value = prop.get(obj)
        val jsonValue = prop.getSerializer()?.toJsonValue(value) ?: value
        serializePropertyValue(jsonValue)
    }

    private fun serializePropertyValue(jsonValue: Any?) {
        TODO("Not yet implemented")
    }

    private fun serializeString(name: String) {

    }

    fun KProperty<*>.getSerializer(): ValueSerializer<Any?>? {
        val customSerializerAnn = findAnnotation<CustomSerializer>() ?: return null
        val serializerClass = customSerializerAnn.serializerClass
        val valueSerializer = serializerClass.objectInstance ?: serializerClass.createInstance()

        @Suppress("UNCHECKED_CAST")
        return valueSerializer as ValueSerializer<Any?>
    }


}