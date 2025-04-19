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

    override fun toString(): String = "${currency()} $amount"

    fun times(multiplier: Int): Money = Money(amount * multiplier, currency())

    fun plus(addend: Money): Sum = Sum(this, addend)

    companion object {
        fun dollar(amount:Int) = Money(amount, "USD")
        fun franc(amount:Int) = Money(amount, "CHF")
    }
}

interface Expression

class Bank {
    fun reduce(source: Expression, target: String): Money {
        val sum = source as Sum
        return sum.reduce(target)
    }
}

class Sum(
    val augend: Money,
    val addend: Money,
): Expression {
    fun reduce(target: String) =
        Money(augend.amount + addend.amount, target)
}