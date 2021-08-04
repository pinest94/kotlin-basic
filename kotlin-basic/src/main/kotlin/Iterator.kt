fun isLetter(c: Char) = c in 'a'..'z' || c in 'A'..'Z'
fun isDigit(c: Int) = c in 0..9
fun recognize(c: Char) = when(c) {
    in '0'..'9' -> "It's a digit"
    in 'a'..'z', in 'A'..'Z'-> "It's a letter!"
    else -> "idk"
}
