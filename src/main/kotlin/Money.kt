package org.money

open class Money(
    open val amount: Int,
    open val currency: String,
) {
    fun currency() = currency

    override fun equals(other: Any?): Boolean =
        (other is Money) &&
        (amount == other.amount) &&
        (currency() == other.currency())

    override fun hashCode(): Int = amount

    fun times(multiplier: Int): Money {
        return Money(amount * multiplier, currency())
    }

    companion object {
        fun dollar(amount:Int) = Dollar(amount, "USD")
        fun franc(amount:Int) = Franc(amount, "CHF")
    }
}

class Dollar(amount: Int, currency: String) : Money(amount, currency)

class Franc(amount: Int, currency: String): Money(amount, currency)

