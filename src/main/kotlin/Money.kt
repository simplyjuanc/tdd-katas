package org.money

open class Money(open val amount: Int) {
    override fun equals(other: Any?): Boolean =
        (other is Money) && (this.amount == other.amount)

    override fun hashCode(): Int {
        return amount
    }
}

data class Dollar(override val amount: Int) : Money(amount) {
    fun times(multiplier: Int) = Dollar(this.amount * multiplier)
}

data class Franc(override val amount: Int): Money(amount) {
    fun times(multiplier: Int) = Franc(this.amount * multiplier)
}