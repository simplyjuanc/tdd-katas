import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test
import org.money.Bank
import org.money.CurrencyPair
import org.money.Expression
import org.money.Money
import org.money.Sum

class MoneyTest {
    @Test
    fun `should assert equality for the same currencies and values`() {
        assertEquals(Money.dollar(5), Money.dollar(5))
        assertEquals(Money.franc(999), Money.franc(999))
    }

    @Test
    fun `should assert inequality between different currencies and values`() {
        assertNotEquals(Money.franc(1), Money.dollar(1))
        assertNotEquals(Money.franc(8), Money.franc(14))
        assertNotEquals(Money.dollar(3), Money.dollar(4))
    }

    @Test
    fun `should return the correct currency`() {
        assertEquals("USD", Money.dollar(1).currency())
        assertEquals("CHF", Money.franc(1).currency())
    }

    @Test
    fun `should multiply dollars correctly`() {
        val dollar = Money.dollar(5)

        assertEquals(Money.dollar(10), dollar.times(2))
        assertEquals(Money.dollar(15), dollar.times(3))
    }

    @Test
    fun `should multiply francs correctly`() {
        val franc = Money.franc(5)

        assertEquals(Money.franc(10), franc.times(2))
        assertEquals(Money.franc(15), franc.times(3))
    }

    @Test
    fun `adding return a Sum object`() {
        val five = Money.dollar(5)
        val sum = five.plus(five)
        assertEquals(five, sum.augend)
        assertEquals(five, sum.addend)
    }

    @Test
    fun `bank should reduce an expression`(){
        val sum = Sum(Money.dollar(7), Money.dollar(5))
        val result = Bank().reduce(sum, "USD")
        assertEquals(Money.dollar(12), result)
    }

    @Test
    fun `bank should reduce Money`() {
        val result = Bank().reduce(Money(1, "USD"), "USD")
        assertEquals(Money.dollar(1), result)
    }

    @Test
    fun `bank should reduce different 2 francs to 1 usd`() {
        val bank = Bank()
        bank.addRate(CurrencyPair("CHF", "USD"), 2)
        val result = bank.reduce(Money.franc(2), "USD")
        assertEquals(Money.dollar(1), result)
    }

    @Test
    fun `a dollar should be a dollar`() {
        assertEquals(1, Bank().rate("USD", "USD"))
    }

    @Test
    fun `should add mixed currencies`() {
        val fiveDollars: Expression = Money.dollar(5)
        val tenFrancs: Expression = Money.franc(10)

        val bank = Bank()
        bank.addRate(CurrencyPair("CHF", "USD"), 2)
        val result = bank.reduce(fiveDollars.plus( tenFrancs), "USD")

        assertEquals(Money.dollar(10),result)
    }

    @Test
    fun `should correctly add sequential sums`() {
        val fiveDollars: Expression = Money.dollar(5)
        val tenFrancs: Expression = Money.franc(10)

        val bank = Bank()
        bank.addRate(CurrencyPair("CHF", "USD"), 2)

        val sum: Expression = Sum(fiveDollars, tenFrancs).plus(fiveDollars)
        val result = bank.reduce(sum, "USD")

        assertEquals(Money.dollar(15), result)

    }
}

