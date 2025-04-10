import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test
import org.money.Dollar

class DollarTest {
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
}