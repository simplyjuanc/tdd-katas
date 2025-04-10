import org.example.Dollar
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class DollarTest {
    @Test
    fun `should multiply dollars correctly`() {
        val dollarFive = Dollar(5)

        assertEquals(Dollar(10), dollarFive.times(2))
        assertEquals(Dollar(15), dollarFive.times(3))
    }
}