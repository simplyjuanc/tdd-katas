package org.money

data class Dollar(val amount: Int) {
    fun times(multiplier: Int) = Dollar(this.amount * multiplier)
}