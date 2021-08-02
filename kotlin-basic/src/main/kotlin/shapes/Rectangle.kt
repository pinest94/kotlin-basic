class Rectangle(val height: Int, val width: Int) {
    val idSquare: Boolean
        get() = height == width
}