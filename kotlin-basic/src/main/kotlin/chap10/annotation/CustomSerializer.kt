package chap10.annotation

import chap10.serialize.ValueSerializer
import kotlin.reflect.KClass

annotation class CustomSerializer(
    val serializerClass: KClass<out ValueSerializer<*>>
)
