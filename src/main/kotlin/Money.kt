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

    override fun plus(addend: Expression): Sum = Sum(this, addend)

    fun times(multiplier: Int): Expression = Money(amount * multiplier, currency())

    companion object {
        fun dollar(amount:Int) = Money(amount, "USD")
        fun franc(amount:Int) = Money(amount, "CHF")
    }
}

interface Expression {
    fun reduce(bank: Bank, target: String): Money
    fun plus(target: Expression): Expression

}

class Sum(
    val augend: Expression,
    val addend: Expression,
): Expression {
    override fun reduce(bank:Bank, target: String) =
        Money(
            augend.reduce(bank, target).amount + addend.reduce(bank, target).amount,
            target
        )

    override fun plus(target: Expression): Expression {
        TODO("Not yet implemented")
    }
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