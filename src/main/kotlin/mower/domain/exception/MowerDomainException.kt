package org.francescfe.mower.domain.exception

class MowerDomainException(message: String) : RuntimeException(message) {
    companion object {
        fun invalidPosition(
            x: Int,
            y: Int,
        ) = MowerDomainException("Invalid position ($x, $y). Coordinates must be >= 0")

        fun invalidInstruction(value: Char) = MowerDomainException("Invalid instruction '$value'")
    }
}
