package org.francescfe.mowerrobot.domain.mower

import org.francescfe.mowerrobot.domain.mower.exception.MowerDomainException

@JvmInline
value class Instruction(val value: Char) {
    init {
        require(value in VALID_INSTRUCTIONS) {
            throw MowerDomainException.invalidInstruction(value)
        }
    }

    fun isTurnLeft() = value == 'L'

    fun isTurnRight() = value == 'R'

    fun isMove() = value == 'M'

    companion object {
        private val VALID_INSTRUCTIONS = setOf('L', 'R', 'M')

        fun fromChar(value: Char): Instruction = Instruction(value)
    }
}
