package org.money

import java.util.Hashtable
import javax.management.relation.RelationNotFoundException

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

    override fun reduce(bank: Bank, target: String): Money {
        val rate = bank.rate(currency(), target)
        return Money(this.amount / rate, target)
    }

    fun times(multiplier: Int): Money = Money(amount * multiplier, currency())

    fun plus(addend: Money): Sum = Sum(this, addend)

    companion object {
        fun dollar(amount:Int) = Money(amount, "USD")
        fun franc(amount:Int) = Money(amount, "CHF")
    }
}

interface Expression {
    fun reduce(bank: Bank, target: String): Money
}

class Sum(
    val augend: Money,
    val addend: Money,
): Expression {
    override fun reduce(bank:Bank, target: String) =
        Money(
            augend.reduce(bank, target).amount + addend.reduce(bank, target).amount,
            target
        )
}


class Bank {
    private val hashTable = Hashtable<CurrencyPair,Int>()

    fun reduce(source: Expression, target: String): Money = source.reduce(this, target)

    fun rate(source: String, target: String): Int =
        if (source == target) 1 else {
            hashTable.get(CurrencyPair(source, target)) ?: throw RelationNotFoundException()
        }

    fun addRate(pair: CurrencyPair, rate: Int) {
        hashTable.put(pair, rate)
    }
}


data class CurrencyPair(
    val from: String,
    val to: String,
)