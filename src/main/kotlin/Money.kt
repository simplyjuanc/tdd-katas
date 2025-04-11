package org.money

abstract class Money(open val amount: Int) {
    override fun equals(other: Any?): Boolean =
        (other is Money) && (this.amount == other.amount)
    override fun hashCode(): Int = amount

    abstract fun times(multiplier: Int): Money
    abstract fun currency(): String

    companion object {
        fun dollar(amount:Int) = Dollar(amount)

        fun franc(amount:Int) = Franc(amount)
    }
}

class Dollar(override val amount: Int) : Money(amount) {
    override fun times(multiplier: Int) = Dollar(this.amount * multiplier)

    override fun currency(): String = "USD"
}

class Franc(override val amount: Int): Money(amount) {
    override fun times(multiplier: Int) = Franc(this.amount * multiplier)

    override fun currency(): String = "CHF"
}

