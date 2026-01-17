package org.francescfe.mowerrobot.domain.mower.exception

class MowerDomainException(message: String) : RuntimeException(message) {
    companion object {
        fun invalidInstruction(value: Char) = MowerDomainException("Invalid instruction '$value'")
    }
}
