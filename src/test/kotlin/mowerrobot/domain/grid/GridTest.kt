package mowerrobot.domain.grid

import org.francescfe.mowerrobot.domain.grid.Grid
import org.francescfe.mowerrobot.domain.spatial.Position
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class GridTest {
    @Test
    fun `position inside grid is valid`() {
        val grid = Grid(5, 5)
        val position = Position(3, 2)

        assertTrue(grid.isWithinBounds(position))
    }

    @Test
    fun `position outside grid on x axis is invalid`() {
        val grid = Grid(5, 5)
        val position = Position(6, 2)

        assertFalse(grid.isWithinBounds(position))
    }

    @Test
    fun `position outside grid on y axis is invalid`() {
        val grid = Grid(5, 5)
        val position = Position(3, 6)

        assertFalse(grid.isWithinBounds(position))
    }

    @Test
    fun `position with negative x is invalid`() {
        val grid = Grid(5, 5)
        val position = Position(-1, 2)

        assertFalse(grid.isWithinBounds(position))
    }

    @Test
    fun `position with negative y is invalid`() {
        val grid = Grid(5, 5)
        val position = Position(2, -1)

        assertFalse(grid.isWithinBounds(position))
    }

    @Test
    fun `should allow grid with zero dimensions`() {
        val grid = Grid(0, 0)

        assertEquals(0, grid.upperRightX)
        assertEquals(0, grid.upperRightY)
    }

    @Test
    fun `should reject negative upperRightX`() {
        assertFailsWith<IllegalArgumentException> {
            Grid(-1, 5)
        }
    }

    @Test
    fun `should reject negative upperRightY`() {
        assertFailsWith<IllegalArgumentException> {
            Grid(5, -1)
        }
    }

    @Test
    fun `minimal grid allows only origin position`() {
        val grid = Grid(0, 0)

        assertTrue(grid.isWithinBounds(Position(0, 0)))
        assertFalse(grid.isWithinBounds(Position(1, 0)))
        assertFalse(grid.isWithinBounds(Position(0, 1)))
    }

    @Test
    fun `asymmetric grid validates correctly`() {
        val grid = Grid(3, 7)

        assertTrue(grid.isWithinBounds(Position(3, 7)))
        assertTrue(grid.isWithinBounds(Position(2, 6)))
        assertFalse(grid.isWithinBounds(Position(4, 5)))
        assertFalse(grid.isWithinBounds(Position(2, 8)))
    }
}
