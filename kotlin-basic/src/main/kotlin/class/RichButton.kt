package `class`

import interfaces.Clickable

class RichButton : Clickable {

    fun disable() {}

    open fun animate() {}

    override fun click() {}
}

abstract class Validation {
    abstract fun checkNation()
    open fun checkId() {
        println("check id")
    }
    fun checkPassword() {
        println("check password")
    }
}

open class AccountValidation: Validation() {
    override fun checkNation() {
        println("check nation")
    }

    override fun checkId() {
        super.checkId()
        println("account id check")
    }
}

fun main() {
    val validation = AccountValidation()
    println(validation.checkPassword())
}