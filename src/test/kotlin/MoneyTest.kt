import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test
import org.money.Dollar
import org.money.Franc

class MoneyTest {
    @Test
    fun `should multiply dollars correctly`() {
        val dollar = Dollar(5)

        assertEquals(Dollar(10), dollar.times(2))
        assertEquals(Dollar(15), dollar.times(3))
    }

    @Test
    fun `should assert equality between dollars`() {
        assertEquals(Dollar(5), Dollar(5))
        assertNotEquals(Dollar(7), Dollar(6))
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
        assertEquals(Franc(5), Dollar(5))
        assertNotEquals(Franc(1), Dollar(14))
    }
}