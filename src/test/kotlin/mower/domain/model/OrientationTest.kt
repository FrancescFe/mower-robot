package mower.domain.model

import org.francescfe.mower.domain.model.Orientation
import kotlin.test.Test
import kotlin.test.assertEquals

class OrientationTest {
    @Test
    fun `should turn left from North to West`() {
        assertEquals(Orientation.W, Orientation.N.turnLeft())
    }

    @Test
    fun `should turn left from West to South`() {
        assertEquals(Orientation.S, Orientation.W.turnLeft())
    }

    @Test
    fun `should turn left from South to East`() {
        assertEquals(Orientation.E, Orientation.S.turnLeft())
    }

    @Test
    fun `should turn left from East to North`() {
        assertEquals(Orientation.N, Orientation.E.turnLeft())
    }

    @Test
    fun `should turn right from North to East`() {
        assertEquals(Orientation.E, Orientation.N.turnRight())
    }

    @Test
    fun `should turn right from East to South`() {
        assertEquals(Orientation.S, Orientation.E.turnRight())
    }

    @Test
    fun `should turn right from South to West`() {
        assertEquals(Orientation.W, Orientation.S.turnRight())
    }

    @Test
    fun `should turn right from West to North`() {
        assertEquals(Orientation.N, Orientation.W.turnRight())
    }
}
