package chap10

data class Book(
    val title: String,
    val author: Author
)

data class Author(
    val name: String
)