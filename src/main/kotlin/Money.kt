package org.money

open class Money(open val amount: Int) {
    override fun equals(other: Any?): Boolean =
        (other is Money) && (this.amount == other.amount)

    override fun hashCode(): Int {
        return amount
    }

    companion object {
        fun dollar(amount:Int) = Dollar(amount)

        fun franc(amount:Int) = Franc(amount)
    }
}

class Dollar(override val amount: Int) : Money(amount) {
    fun times(multiplier: Int):Money = Dollar(this.amount * multiplier)
}

class Franc(override val amount: Int): Money(amount) {
    fun times(multiplier: Int):Money = Franc(this.amount * multiplier)
}

