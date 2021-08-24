package nulls

class NullDemo {

    fun stringToUpper(str: String?): String? = str?.toUpperCase()

    fun helloMessage(name: String?) = name ?: "kotlin"

    /***
     * 안전한 연산자를 사용하여 equals 구현하기
     */
    class Person(val firstName: String, val lastName: String) {
        override fun equals(other: Any?): Boolean {
            val otherPerson = other as? Person ?: return false

            return otherPerson.firstName == firstName && otherPerson.lastName == lastName
        }

        override fun hashCode(): Int {
            return super.hashCode() * 37 + lastName.hashCode()
        }
    }
}