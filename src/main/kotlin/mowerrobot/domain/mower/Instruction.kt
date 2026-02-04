package org.francescfe.mowerrobot.domain.mower

import org.francescfe.mowerrobot.domain.mower.exception.MowerDomainException

sealed class Instruction {
    object TurnLeft : Instruction()

    object TurnRight : Instruction()

    object Move : Instruction()

    object MoveBackwards : Instruction()

    companion object {
        fun fromChar(value: Char): Instruction =
            when (value) {
                'L' -> TurnLeft
                'R' -> TurnRight
                'M' -> Move
                'B' -> MoveBackwards
                else -> throw MowerDomainException.invalidInstruction(value)
            }
    }
}
