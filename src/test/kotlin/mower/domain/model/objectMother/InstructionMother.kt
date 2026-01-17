package mower.domain.model.objectMother

import org.francescfe.mower.domain.model.Instruction

object InstructionMother {
    fun turnLeft() = Instruction('L')

    fun turnRight() = Instruction('R')

    fun move() = Instruction('M')

    fun listFrom(vararg chars: Char): List<Instruction> = chars.map { Instruction(it) }
}
