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

    override fun reduce(target: String): Money = this

    fun times(multiplier: Int): Money = Money(amount * multiplier, currency())

    fun plus(addend: Money): Sum = Sum(this, addend)

    companion object {
        fun dollar(amount:Int) = Money(amount, "USD")
        fun franc(amount:Int) = Money(amount, "CHF")
    }
}

interface Expression {
    fun reduce(target: String): Money
}

class Bank {
    fun reduce(source: Expression, target: String): Money = source.reduce(target)
}

class Sum(
    val augend: Money,
    val addend: Money,
): Expression {
    override fun reduce(target: String) =
        Money(augend.amount + addend.amount, target)
}