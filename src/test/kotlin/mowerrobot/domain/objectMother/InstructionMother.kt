package mowerrobot.domain.objectMother

import org.francescfe.mowerrobot.domain.mower.Instruction

object InstructionMother {
    fun turnLeft(): Instruction = Instruction.TurnLeft

    fun turnRight(): Instruction = Instruction.TurnRight

    fun move(): Instruction = Instruction.Move

    fun listFrom(vararg chars: Char): List<Instruction> = chars.map { Instruction.fromChar(it) }
}
