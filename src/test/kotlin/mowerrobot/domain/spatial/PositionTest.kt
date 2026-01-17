package mowerrobot.domain.spatial

import mowerrobot.domain.objectMother.PositionMother
import kotlin.test.Test
import kotlin.test.assertEquals

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
}
