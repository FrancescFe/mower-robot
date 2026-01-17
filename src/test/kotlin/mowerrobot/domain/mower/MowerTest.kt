package mowerrobot.domain.mower

import mowerrobot.domain.objectMother.GridMother
import mowerrobot.domain.objectMother.InstructionMother
import mowerrobot.domain.objectMother.MowerMother
import mowerrobot.domain.objectMother.PositionMother
import org.francescfe.mowerrobot.domain.spatial.Orientation
import kotlin.test.Test
import kotlin.test.assertEquals

class MowerTest {
    private val grid = GridMother.default()

    @Test
    fun `should rotate left when executing L instruction`() {
        val mower = MowerMother.atOriginFacingNorth()

        val result = mower.execute(InstructionMother.turnLeft(), grid)

        assertEquals(Orientation.W, result.orientation)
        assertEquals(mower.position, result.position)
    }

    @Test
    fun `should rotate right when executing R instruction`() {
        val mower = MowerMother.atOriginFacingNorth()

        val result = mower.execute(InstructionMother.turnRight(), grid)

        assertEquals(Orientation.E, result.orientation)
        assertEquals(mower.position, result.position)
    }

    @Test
    fun `should move forward when executing M instruction facing North`() {
        val mower = MowerMother.at(1, 2, Orientation.N)

        val result = mower.execute(InstructionMother.move(), grid)

        assertEquals(PositionMother.at(1, 3), result.position)
        assertEquals(Orientation.N, result.orientation)
    }

    @Test
    fun `should move forward when executing M instruction facing South`() {
        val mower = MowerMother.at(1, 2, Orientation.S)

        val result = mower.execute(InstructionMother.move(), grid)

        assertEquals(PositionMother.at(1, 1), result.position)
    }

    @Test
    fun `should move forward when executing M instruction facing East`() {
        val mower = MowerMother.at(1, 2, Orientation.E)

        val result = mower.execute(InstructionMother.move(), grid)

        assertEquals(PositionMother.at(2, 2), result.position)
    }

    @Test
    fun `should move forward when executing M instruction facing West`() {
        val mower = MowerMother.at(1, 2, Orientation.W)

        val result = mower.execute(InstructionMother.move(), grid)

        assertEquals(PositionMother.at(0, 2), result.position)
    }

    @Test
    fun `should execute sequence of instructions`() {
        val mower = MowerMother.at(1, 2, Orientation.N)
        val instructions = InstructionMother.listFrom('L', 'M', 'L', 'M', 'L', 'M', 'L', 'M', 'M')

        val result = mower.executeAll(instructions, grid)

        assertEquals(PositionMother.at(1, 3), result.position)
        assertEquals(Orientation.N, result.orientation)
    }

    @Test
    fun `should return same mower when no instructions`() {
        val mower = MowerMother.atOriginFacingNorth()

        val result = mower.executeAll(emptyList(), grid)

        assertEquals(mower.position, result.position)
        assertEquals(mower.orientation, result.orientation)
    }

    @Test
    fun `should be immutable - original mower unchanged after execute`() {
        val original = MowerMother.at(1, 2, Orientation.N)

        original.execute(InstructionMother.move(), grid)

        assertEquals(PositionMother.at(1, 2), original.position)
        assertEquals(Orientation.N, original.orientation)
    }

    @Test
    fun `should not move when at boundary`() {
        val grid = GridMother.of(5, 5)
        val mower = MowerMother.at(2, 5, Orientation.N)

        val result = mower.execute(InstructionMother.move(), grid)

        assertEquals(PositionMother.at(2, 5), result.position)
    }
}
