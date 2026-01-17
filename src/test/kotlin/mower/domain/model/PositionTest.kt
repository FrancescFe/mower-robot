package mower.domain.model

import mower.domain.model.objectMother.PositionMother
import org.francescfe.mower.domain.exception.MowerDomainException
import org.francescfe.mower.domain.model.Position
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class PositionTest {
    @Test
    fun `should create position with valid coordinates`() {
        val position = PositionMother.at(3, 5)

        assertEquals(3, position.x)
        assertEquals(5, position.y)
    }

    @Test
    fun `should allow position at origin`() {
        val position = PositionMother.origin()

        assertEquals(0, position.x)
        assertEquals(0, position.y)
    }

    @Test
    fun `should reject negative x coordinate`() {
        assertFailsWith<MowerDomainException> {
            Position(x = -1, y = 0)
        }
    }

    @Test
    fun `should reject negative y coordinate`() {
        assertFailsWith<MowerDomainException> {
            Position(x = 0, y = -1)
        }
    }

    @Test
    fun `should move north incrementing y`() {
        val position = PositionMother.at(2, 3)

        val result = position.moveNorth()

        assertEquals(PositionMother.at(2, 4), result)
    }

    @Test
    fun `should move south decrementing y`() {
        val position = PositionMother.at(2, 3)

        val result = position.moveSouth()

        assertEquals(PositionMother.at(2, 2), result)
    }

    @Test
    fun `should move east incrementing x`() {
        val position = PositionMother.at(2, 3)

        val result = position.moveEast()

        assertEquals(PositionMother.at(3, 3), result)
    }

    @Test
    fun `should move west decrementing x`() {
        val position = PositionMother.at(2, 3)

        val result = position.moveWest()

        assertEquals(PositionMother.at(1, 3), result)
    }
}
