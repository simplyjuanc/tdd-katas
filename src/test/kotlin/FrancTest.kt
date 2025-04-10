import org.money.org.money.Franc
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class FrancTest {
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

}