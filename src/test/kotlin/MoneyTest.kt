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
        val franc = Franc(5)

        assertEquals(Franc(10), franc.times(2))
        assertEquals(Franc(15), franc.times(3))
    }

    @Test
    fun `should assert equality between francs`() {
        assertEquals(Franc(5), Franc(5))
        assertNotEquals(Franc(7), Franc(6))
    }

    @Test
    fun `should assert equality between dollars and francs`() {
        assertEquals(Franc(5), Money.dollar(5))
        assertNotEquals(Franc(1), Money.dollar(14))
    }
}