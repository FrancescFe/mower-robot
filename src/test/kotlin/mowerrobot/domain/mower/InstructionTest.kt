package mowerrobot.domain.mower

import mowerrobot.domain.objectMother.InstructionMother
import org.francescfe.mowerrobot.domain.mower.Instruction
import org.francescfe.mowerrobot.domain.mower.exception.MowerDomainException
import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class InstructionTest {
    @Test
    fun `should create valid turn left instruction`() {
        val instruction = InstructionMother.turnLeft()

        assertTrue(instruction.isTurnLeft())
        assertFalse(instruction.isTurnRight())
        assertFalse(instruction.isMove())
    }

    @Test
    fun `should create valid turn right instruction`() {
        val instruction = InstructionMother.turnRight()

        assertFalse(instruction.isTurnLeft())
        assertTrue(instruction.isTurnRight())
        assertFalse(instruction.isMove())
    }

    @Test
    fun `should create valid move instruction`() {
        val instruction = InstructionMother.move()

        assertFalse(instruction.isTurnLeft())
        assertFalse(instruction.isTurnRight())
        assertTrue(instruction.isMove())
    }

    @Test
    fun `should reject invalid instruction character`() {
        assertFailsWith<MowerDomainException> {
            Instruction('X')
        }
    }

    @Test
    fun `should reject lowercase instruction`() {
        assertFailsWith<MowerDomainException> {
            Instruction('l')
        }
    }

    @Test
    fun `should create instruction from char using factory method`() {
        val instruction = Instruction.fromChar('M')

        assertTrue(instruction.isMove())
    }
}
