package org.francescfe.mower.domain.model

import org.francescfe.mower.domain.exception.MowerDomainException

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
