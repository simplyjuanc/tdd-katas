package org.money

data class Dollar(override val amount: Int) : Money(amount) {
    fun times(multiplier: Int) = Dollar(this.amount * multiplier)
}