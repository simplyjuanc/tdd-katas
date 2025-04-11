package org.money

abstract class Money(
    open val amount: Int,
    open val currency: String,
) {
    fun currency() = currency

    override fun equals(other: Any?): Boolean =
        (other is Money) &&
        (amount == other.amount) &&
        (currency == other.currency())

    override fun hashCode(): Int = amount

    abstract fun times(multiplier: Int): Money

    companion object {
        fun dollar(amount:Int) = Dollar(amount, "USD")
        fun franc(amount:Int) = Franc(amount, "CHF")
    }
}

class Dollar(amount: Int, currency: String) : Money(amount, currency) {
    override fun times(multiplier: Int) = Dollar(amount * multiplier, currency)
}

class Franc(amount: Int, currency: String): Money(amount, currency) {
    override fun times(multiplier: Int) = Franc(amount * multiplier, currency)
}

