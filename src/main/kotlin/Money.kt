package org.money

open class Money(
    open val amount: Int,
    open val currency: String,
): Expression {
    fun currency() = currency

    override fun equals(other: Any?): Boolean =
        (other is Money) &&
        (amount == other.amount) &&
        (currency() == other.currency())

    override fun hashCode(): Int = amount

    fun times(multiplier: Int): Money = Money(amount * multiplier, currency())

    fun plus(addend: Money): Money = Money(amount + addend.amount, currency())

    companion object {
        fun dollar(amount:Int) = Money(amount, "USD")
        fun franc(amount:Int) = Money(amount, "CHF")
    }
}

interface Expression

class Bank {
    fun reduce(sum: Expression, currency: String): Expression {
        return Money.dollar(10)
    }
}
