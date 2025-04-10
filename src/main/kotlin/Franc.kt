package org.money

data class Franc(override val amount: Int):Money(amount) {
    fun times(multiplier: Int) = Franc(this.amount * multiplier)
}
