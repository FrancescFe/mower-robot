package org.francescfe.mower.domain.model

import org.francescfe.mower.domain.exception.MowerDomainException

data class Position(
    val x: Int,
    val y: Int,
) {
    init {
        require(x >= 0) { throw MowerDomainException.invalidPosition(x, y) }
        require(y >= 0) { throw MowerDomainException.invalidPosition(x, y) }
    }
}
