package mowerrobot.domain.objectMother

import org.francescfe.mowerrobot.domain.mower.Instruction

object InstructionMother {
    fun turnLeft() = Instruction('L')

    fun turnRight() = Instruction('R')

    fun move() = Instruction('M')

    fun listFrom(vararg chars: Char): List<Instruction> = chars.map { Instruction(it) }
}
