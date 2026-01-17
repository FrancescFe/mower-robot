package mower.domain.model

import mower.domain.model.objectMother.InstructionMother
import mower.domain.model.objectMother.MowerMother
import mower.domain.model.objectMother.PositionMother
import org.francescfe.mower.domain.model.Orientation
import kotlin.test.Test
import kotlin.test.assertEquals

class MowerTest {
    @Test
    fun `should rotate left when executing L instruction`() {
        val mower = MowerMother.atOriginFacingNorth()

        val result = mower.execute(InstructionMother.turnLeft())

        assertEquals(Orientation.W, result.orientation)
        assertEquals(mower.position, result.position)
    }

    @Test
    fun `should rotate right when executing R instruction`() {
        val mower = MowerMother.atOriginFacingNorth()

        val result = mower.execute(InstructionMother.turnRight())

        assertEquals(Orientation.E, result.orientation)
        assertEquals(mower.position, result.position)
    }

    @Test
    fun `should move forward when executing M instruction facing North`() {
        val mower = MowerMother.at(1, 2, Orientation.N)

        val result = mower.execute(InstructionMother.move())

        assertEquals(PositionMother.at(1, 3), result.position)
        assertEquals(Orientation.N, result.orientation)
    }

    @Test
    fun `should move forward when executing M instruction facing South`() {
        val mower = MowerMother.at(1, 2, Orientation.S)

        val result = mower.execute(InstructionMother.move())

        assertEquals(PositionMother.at(1, 1), result.position)
    }

    @Test
    fun `should move forward when executing M instruction facing East`() {
        val mower = MowerMother.at(1, 2, Orientation.E)

        val result = mower.execute(InstructionMother.move())

        assertEquals(PositionMother.at(2, 2), result.position)
    }

    @Test
    fun `should move forward when executing M instruction facing West`() {
        val mower = MowerMother.at(1, 2, Orientation.W)

        val result = mower.execute(InstructionMother.move())

        assertEquals(PositionMother.at(0, 2), result.position)
    }

    @Test
    fun `should execute sequence of instructions`() {
        val mower = MowerMother.at(1, 2, Orientation.N)
        val instructions = InstructionMother.listFrom('L', 'M', 'L', 'M', 'L', 'M', 'L', 'M', 'M')

        val result = mower.executeAll(instructions)

        assertEquals(PositionMother.at(1, 3), result.position)
        assertEquals(Orientation.N, result.orientation)
    }

    @Test
    fun `should return same mower when no instructions`() {
        val mower = MowerMother.atOriginFacingNorth()

        val result = mower.executeAll(emptyList())

        assertEquals(mower.position, result.position)
        assertEquals(mower.orientation, result.orientation)
    }

    @Test
    fun `should be immutable - original mower unchanged after execute`() {
        val original = MowerMother.at(1, 2, Orientation.N)

        original.execute(InstructionMother.move())

        assertEquals(PositionMother.at(1, 2), original.position)
        assertEquals(Orientation.N, original.orientation)
    }
}
