package mowerrobot.domain.mower

import mowerrobot.domain.objectMother.InstructionMother
import org.francescfe.mowerrobot.domain.mower.Instruction
import org.francescfe.mowerrobot.domain.mower.exception.MowerDomainException
import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertIs
import kotlin.test.assertSame

class InstructionTest {
    @Test
    fun `should create turn left instruction`() {
        val instruction = InstructionMother.turnLeft()

        assertIs<Instruction.TurnLeft>(instruction)
    }

    @Test
    fun `should create turn right instruction`() {
        val instruction = InstructionMother.turnRight()

        assertIs<Instruction.TurnRight>(instruction)
    }

    @Test
    fun `should create move instruction`() {
        val instruction = InstructionMother.move()

        assertIs<Instruction.Move>(instruction)
    }

    @Test
    fun `should move backwards when executing B instruction`() {
        val instruction = InstructionMother.moveBackwards()

        assertIs<Instruction.MoveBackwards>(instruction)
    }

    @Test
    fun `should reject invalid instruction character`() {
        assertFailsWith<MowerDomainException> {
            Instruction.fromChar('X')
        }
    }

    @Test
    fun `should reject lowercase instruction`() {
        assertFailsWith<MowerDomainException> {
            Instruction.fromChar('l')
        }
    }

    @Test
    fun `should create instruction from char using factory method`() {
        val instruction = Instruction.fromChar('M')

        assertIs<Instruction.Move>(instruction)
    }

    @Test
    fun `turn left instructions are same instance`() {
        assertSame(Instruction.TurnLeft, Instruction.fromChar('L'))
    }

    @Test
    fun `turn right instructions are same instance`() {
        assertSame(Instruction.TurnRight, Instruction.fromChar('R'))
    }

    @Test
    fun `move instructions are same instance`() {
        assertSame(Instruction.Move, Instruction.fromChar('M'))
    }
}
