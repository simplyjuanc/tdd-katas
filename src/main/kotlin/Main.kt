package org.example

fun main() {
    println("Hello World!")
}

data class Dollar(val amount: Int) {
    fun times(multiplier: Int) = Dollar(this.amount * multiplier)
}
