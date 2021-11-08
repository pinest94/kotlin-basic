package chap7

data class Point(val x: Int, val y: Int) {
    operator fun plus(other: Point): Point {
        return Point(x + other.x, y + other.y)
    }

    operator fun times(scale: Double): Point {
        return Point((x * scale).toInt(), (y * scale).toInt())
    }

    operator fun plusAssign(value: Int) : Point {
        return Point(x+=value, y+=value)
    }
}

fun main() {
    val p1 = Point(20, 30)
    println(p1 * 3.3)
}