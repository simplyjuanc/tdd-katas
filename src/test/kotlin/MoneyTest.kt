import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test
import org.money.Franc
import org.money.Money

class MoneyTest {
    @Test
    fun `should multiply dollars correctly`() {
        val dollar = Money.dollar(5)

        assertEquals(Money.dollar(10), dollar.times(2))
        assertEquals(Money.dollar(15), dollar.times(3))
    }

    @Test
    fun `should assert equality between dollars`() {
        assertEquals(Money.dollar(5), Money.dollar(5))
        assertNotEquals(Money.dollar(7), Money.dollar(6))
    }

    @Test
    fun `should multiply francs correctly`() {
        val franc = Money.franc(5)

        assertEquals(Money.franc(10), franc.times(2))
        assertEquals(Money.franc(15), franc.times(3))
    }

    @Test
    fun `should assert equality between francs`() {
        assertEquals(Money.franc(5), Money.franc(5))
        assertNotEquals(Money.franc(7), Money.franc(6))
    }

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
    fun `should test value equality not class equality`() {
        assertEquals(Franc(5, "CHF"), Money.franc(5))
    }
}