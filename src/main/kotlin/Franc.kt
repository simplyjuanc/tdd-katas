package org.money

data class Franc(val amount: Int) {
    fun times(multiplier: Int) = Franc(this.amount * multiplier)
}
