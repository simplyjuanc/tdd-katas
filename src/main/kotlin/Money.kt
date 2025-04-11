package org.money

abstract class Money(open val amount: Int) {
    override fun equals(other: Any?): Boolean =
        (other is Money) && (this.amount == other.amount)

    override fun hashCode(): Int {
        return amount
    }

    abstract fun currency(): String

    companion object {
        fun dollar(amount:Int) = Dollar(amount)

        fun franc(amount:Int) = Franc(amount)
    }
}

class Dollar(override val amount: Int) : Money(amount) {
    fun times(multiplier: Int):Money = Dollar(this.amount * multiplier)

    override fun currency(): String = "USD"
}

class Franc(override val amount: Int): Money(amount) {
    fun times(multiplier: Int):Money = Franc(this.amount * multiplier)

    override fun currency(): String = "CHF"
}

