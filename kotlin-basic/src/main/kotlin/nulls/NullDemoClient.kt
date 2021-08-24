package nulls

fun main() {
    val p1 = NullDemo.Person("Dmitry", "Jemero")
    var p2 = NullDemo.Person("Dmitry", "Jemerov")

    println(p1 == p2)
    println(p1.equals(p2))

    ignoreNulls("hansol")

    val email: String? = null
    email?.let { sendEmailTo(it) }
}

/***
 * 널 아님 단언(not null assertion 사용)
 */
fun ignoreNulls(s: String?) {
    val sNotNull: String = s!!
    println(sNotNull.length)
}

fun sendEmailTo(email: String) {
    println("Sending email to ${email}")
}
