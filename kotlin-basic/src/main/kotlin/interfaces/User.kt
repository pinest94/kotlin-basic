package interfaces

interface User {
    val name: String
}

class PrivateUser(override val name: String) : User

class SubscribingUser(val email: String) : User {
    override val name: String
        get() = email.substringBefore('@')
}

class FacebookUser(accountId: Int) : User {
    override val name = getFacebookName(accountId)

    private fun getFacebookName(accountId: Int): String {
        if (accountId == 92567) return "jeny"
        return "admin"
    }
}

fun main() {
    println(PrivateUser("test@kotlinlang.org").name)
    println(SubscribingUser("test@kotlinlang.org").name)
}