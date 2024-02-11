package chap10.annotation

import kotlin.reflect.KClass

annotation class DeserializeInterface(val targetClass: KClass<out Any>)
